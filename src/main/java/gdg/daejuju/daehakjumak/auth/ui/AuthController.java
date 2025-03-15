package gdg.daejuju.daehakjumak.auth.ui;

import gdg.daejuju.daehakjumak.auth.application.AuthService;
import gdg.daejuju.daehakjumak.auth.application.dto.RefreshTokenRequestDto;
import gdg.daejuju.daehakjumak.auth.application.dto.UserAccessTokenResponseDto;
import gdg.daejuju.daehakjumak.auth.application.dto.KakaoAuthRequestDto;
import gdg.daejuju.daehakjumak.common.domain.exception.ErrorCode;
import gdg.daejuju.daehakjumak.common.ui.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/kakao")
    public Response<UserAccessTokenResponseDto> authenticateWithKakao(@RequestBody KakaoAuthRequestDto dto) {
        UserAccessTokenResponseDto response = authService.processKakaoLogin(dto.accessToken());
        return Response.ok(response);
    }

    @PostMapping("/refresh")
    public Response<UserAccessTokenResponseDto> refreshToken(@RequestBody RefreshTokenRequestDto dto) {
        UserAccessTokenResponseDto response = authService.getNewToken(dto);
        return Response.ok(response);
    }
}
