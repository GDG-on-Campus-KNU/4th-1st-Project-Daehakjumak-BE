package gdg.daejuju.daehakjumak.auth.application;

import gdg.daejuju.daehakjumak.auth.application.dto.UserAccessTokenResponseDto;
import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.KakaoUserInfo;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import gdg.daejuju.daehakjumak.auth.repository.domain.TokenProvider;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final TokenProvider tokenProvider;
    private final RestTemplate restTemplate;
    private final UserAuthRepository userAuthRepository;

    public UserAccessTokenResponseDto processKakaoLogin(String kakaoAccessToken) {
        // 카카오 API로 사용자 정보 요청
        KakaoUserInfo kakaoUserInfo = fetchKakaoUserProfile(kakaoAccessToken);

        // 사용자 정보가 없으면 예외 발생
        if (kakaoUserInfo == null || kakaoUserInfo.getId() == null) {
            throw new IllegalArgumentException("Invalid Kakao token");
        }

        // 사용자 ID로 검색
        User user = userRepository.findByKakaoId(kakaoUserInfo.getId())
                .orElse(userAuthRepository.registerUser(kakaoUserInfo.toUser()));

        // 토큰 생성
        String accessToken = tokenProvider.createToken(user.getId());
        String refreshToken = tokenProvider.createRefreshToken(user.getId());

        return new UserAccessTokenResponseDto(accessToken, refreshToken, tokenProvider.getTokenValidTime() / 1000);
    }

    private KakaoUserInfo fetchKakaoUserProfile(String accessToken) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(accessToken);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<KakaoUserInfo> response = restTemplate.exchange(
                    "https://kapi.kakao.com/v2/user/me",
                    HttpMethod.GET,
                    entity,
                    KakaoUserInfo.class
            );

            return response.getBody();
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch Kakao user profile", e);
        }
    }

}
