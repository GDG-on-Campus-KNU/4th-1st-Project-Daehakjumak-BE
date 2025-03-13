package gdg.daejuju.daehakjumak.auth.repository.entity;

import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.common.repository.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="jumak_user_auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private Long userId;
    private Long kakaoId;
    private LocalDateTime lastLoginDt;

    public UserAuthEntity(Long userId, Long kakaoId) {
        this.userId = userId;
        this.kakaoId = kakaoId;
    }

    public UserAuth toUserAuth() {
        return new UserAuth(userId,kakaoId);
    }

    public void updateLastLoginAt() {
        this.lastLoginDt = LocalDateTime.now();
    }
}

