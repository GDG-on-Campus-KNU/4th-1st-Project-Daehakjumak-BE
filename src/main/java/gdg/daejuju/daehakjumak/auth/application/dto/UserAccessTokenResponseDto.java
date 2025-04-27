package gdg.daejuju.daehakjumak.auth.application.dto;

public record UserAccessTokenResponseDto(String accessToken, String refreshToken, long expiresIn) {
}
