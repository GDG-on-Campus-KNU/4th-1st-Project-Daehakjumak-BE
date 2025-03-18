package gdg.daejuju.daehakjumak.auth.repository.jpa;

import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, Long> {
    Optional<UserAuthEntity> findByKakaoId(Long kakaoId);

    @Query("update UserAuthEntity ae set ae.refreshToken = null where ae.userId =: userId")
    void expireRefreshToken(Long userId);
}
