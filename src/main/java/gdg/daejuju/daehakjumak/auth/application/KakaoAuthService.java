package gdg.daejuju.daehakjumak.auth.application;

import gdg.daejuju.daehakjumak.auth.application.dto.RefreshTokenRequestDto;
import gdg.daejuju.daehakjumak.auth.application.dto.UserAccessTokenResponseDto;
import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.KakaoUserInfo;
import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import gdg.daejuju.daehakjumak.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import gdg.daejuju.daehakjumak.auth.repository.domain.JwtTokenProvider;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RestTemplate restTemplate;
    private final UserAuthRepository userAuthRepository;

    @Transactional
    public UserAccessTokenResponseDto processKakaoLogin(String kakaoAccessToken) {
        // 카카오 API로 사용자 정보 요청
        KakaoUserInfo kakaoUserInfo = getKakaoUserInfo(kakaoAccessToken);

        // 사용자 정보가 없으면 예외 발생
        if (kakaoUserInfo == null || kakaoUserInfo.getId() == null) {
            throw new IllegalArgumentException("Invalid Kakao token");
        }

        Long kakaoId = kakaoUserInfo.getId();

        // 회원가입된 사용자인지 검증(카카오 ID로 확인) 및 처리
        UserAuthEntity userAuthEntity = userAuthRepository.findByKakaoId(kakaoUserInfo.getId())
                .orElseGet(() -> userAuthRepository.registerUser(kakaoUserInfo.toUser(),kakaoId));

        // 토큰 생성
        String accessToken = jwtTokenProvider.createToken(userAuthEntity.getUserId());
        String refreshToken = jwtTokenProvider.createRefreshToken(userAuthEntity.getUserId());

        //로그인 처리
        UserAuth userAuth = userAuthRepository.loginUser(userAuthEntity, refreshToken);

        return new UserAccessTokenResponseDto(accessToken, refreshToken, jwtTokenProvider.getTokenValidTime() / 1000);
    }

    private KakaoUserInfo getKakaoUserInfo(String kakaoAccessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(kakaoAccessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.GET,
                    entity,
                    KakaoUserInfo.class   ///문제임
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Kakao user profile", e);
        }
    }

    public void kakaoLogout(String kakaoAccessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + kakaoAccessToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        restTemplate.exchange("https://kapi.kakao.com/v1/user/logout", HttpMethod.POST, entity, String.class);
    }

}
