package gdg.daejuju.daehakjumak.auth.repository.domain;

import lombok.Getter;

@Getter
public class UserAuth {
    private final Long userId;
    private final Long kakaoId;
    private final String refreshToken;

    public UserAuth(Long userId, Long kakaoId, String refreshToken) {
        this.userId = userId;
        this.kakaoId = kakaoId;
        this.refreshToken = refreshToken;
    }
}
