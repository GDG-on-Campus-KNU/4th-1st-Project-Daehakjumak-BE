package gdg.daejuju.daehakjumak.auth.application;

import gdg.daejuju.daehakjumak.auth.application.dto.RefreshTokenRequestDto;
import gdg.daejuju.daehakjumak.auth.application.dto.UserAccessTokenResponseDto;
import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.JwtTokenProvider;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserAuthRepository userAuthRepository;
    private final KakaoAuthService kakaoAuthService;
    private final UserDetailsService userDetailsService;

    @Transactional
    public UserAccessTokenResponseDto getNewToken(RefreshTokenRequestDto dto){
        String refreshToken = dto.getRefreshToken();
        // 리프레시 토큰에서 사용자 ID 추출
        Long userId = jwtTokenProvider.getUserId(dto.getRefreshToken());

        if(userAuthRepository.isRefreshTokenExpired(userId,refreshToken)){
            throw new IllegalArgumentException("refreshToken is expired");
        }
        // 리프레시 토큰 유효성 검증
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        // 새 액세스 토큰 및 리프레시 토큰 생성
        UserDetails userDetails = userDetailsService.loadUserByUsername(userId.toString()); //refreshToken에는 userId만 있어서 Token생성을 위해 DB에서 authorites 등 필요한 정보 조회
        String newAccessToken = jwtTokenProvider.createToken(userDetails, userId);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(userId);

        // userAuthEntity에 refreshToken 정보 업데이트
        userAuthRepository.updateRefreshToken(newRefreshToken,userId);
        // 응답 생성
        return new UserAccessTokenResponseDto(newAccessToken, newRefreshToken, jwtTokenProvider.getTokenValidTime() / 1000);
    }

    @Transactional
    public void logout(String accessToken) {
        // JWT에서 유저 ID 추출
        Long userId = jwtTokenProvider.getUserId(accessToken);

        // Kakao 로그아웃
        kakaoAuthService.kakaoLogout(accessToken);

        // Refresh Token 삭제
        userAuthRepository.logoutUser(userId); //dirty checking

    }

}




