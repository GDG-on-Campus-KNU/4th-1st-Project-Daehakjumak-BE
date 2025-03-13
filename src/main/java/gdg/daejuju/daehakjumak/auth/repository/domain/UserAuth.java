package gdg.daejuju.daehakjumak.auth.repository.domain;

import lombok.Getter;

@Getter
public class UserAuth {
    private final Long userId;
    private final Long kakaoId;

    public UserAuth(Long userId, Long kakaoId) {
        this.userId = userId;
        this.kakaoId = kakaoId;
    }
}
