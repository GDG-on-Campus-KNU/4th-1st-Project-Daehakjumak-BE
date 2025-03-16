package gdg.daejuju.daehakjumak.auth.application.dto;

import lombok.Getter;

@Getter
public class RefreshTokenRequestDto {
    private String refreshToken;

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
