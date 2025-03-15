package gdg.daejuju.daehakjumak.auth.application;

import gdg.daejuju.daehakjumak.auth.application.dto.RefreshTokenRequestDto;
import gdg.daejuju.daehakjumak.auth.application.dto.UserAccessTokenResponseDto;
import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.KakaoUserInfo;
import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
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

@Service
@RequiredArgsConstructor
public class AuthService {

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

        // 회원가입된 사용자인지 검증(카카오 ID로 확인) 및 처리
        Long kakaoId = kakaoUserInfo.getId();
        UserAuth userAuth = userAuthRepository.loginUser(kakaoUserInfo.toUser(), kakaoId);

        /*UserAuth userAuth = userAuthRepository.findByKakaoId(kakaoId)
                .orElse(userAuthRepository.registerUser(kakaoUserInfo.toUser(), kakaoId));*/

        // 토큰 생성
        String accessToken = jwtTokenProvider.createToken(userAuth.getUserId());
        String refreshToken = jwtTokenProvider.createRefreshToken(userAuth.getUserId());

        return new UserAccessTokenResponseDto(accessToken, refreshToken, jwtTokenProvider.getTokenValidTime() / 1000);
    }

    private KakaoUserInfo getKakaoUserInfo(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
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

    public UserAccessTokenResponseDto getNewToken(RefreshTokenRequestDto dto){
        // 리프레시 토큰 유효성 검증
        if (!jwtTokenProvider.validateToken(dto.getRefreshToken())) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        // 리프레시 토큰에서 사용자 ID 추출
        Long userId = jwtTokenProvider.getUserId(dto.getRefreshToken());


        // 새 액세스 토큰 및 리프레시 토큰 생성
        String newAccessToken = jwtTokenProvider.createToken(userId);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId);

        // 응답 생성
        return new UserAccessTokenResponseDto(newAccessToken, newRefreshToken, jwtTokenProvider.getTokenValidTime() / 1000);
    }

}
