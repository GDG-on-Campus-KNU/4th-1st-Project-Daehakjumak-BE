package gdg.daejuju.daehakjumak.auth.ui;

import gdg.daejuju.daehakjumak.auth.application.AuthService;
import gdg.daejuju.daehakjumak.auth.application.KakaoAuthService;
import gdg.daejuju.daehakjumak.auth.application.dto.RefreshTokenRequestDto;
import gdg.daejuju.daehakjumak.auth.application.dto.UserAccessTokenResponseDto;
import gdg.daejuju.daehakjumak.auth.application.dto.KakaoAuthRequestDto;
import gdg.daejuju.daehakjumak.common.ui.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final KakaoAuthService kakaoAuthService;
    private final AuthService authService;

    @PostMapping("/kakao")
    public Response<UserAccessTokenResponseDto> authenticateWithKakao(@RequestBody KakaoAuthRequestDto dto) {
        UserAccessTokenResponseDto response = kakaoAuthService.processKakaoLogin(dto.accessToken());
        return Response.ok(response);
    }

    @PostMapping("/refresh")
    public Response<UserAccessTokenResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto dto) {
        UserAccessTokenResponseDto response = authService.getNewToken(dto);
        return Response.ok(response);
    }

    @PostMapping("/logout")
    public Response<String> logout(@RequestHeader("Authorization") String accessToken) {
        authService.logout(accessToken);
        return Response.ok("logout complete");
    }
}
