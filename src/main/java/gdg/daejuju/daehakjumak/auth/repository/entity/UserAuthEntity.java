package gdg.daejuju.daehakjumak.auth.repository.entity;

import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.common.repository.TimeBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name="jumak_user_auth")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private Long userId;
    private Long kakaoId;
    private String refreshToken;
    private LocalDateTime lastLoginDt;

    public UserAuthEntity(Long userId, Long kakaoId, String refreshToken) {
        this.userId = userId;
        this.kakaoId = kakaoId;
        this.refreshToken = refreshToken;
    }

    public UserAuthEntity(UserAuth userAuth) {
        this.userId = userAuth.getUserId();
        this.kakaoId = userAuth.getKakaoId();
        this.refreshToken = userAuth.getRefreshToken();
    }

    public UserAuth toUserAuth() {
        return new UserAuth(userId,kakaoId,refreshToken);
    }

    public void updateLastLoginAt() {
        this.lastLoginDt = LocalDateTime.now();
    }

    public void updateRefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}

