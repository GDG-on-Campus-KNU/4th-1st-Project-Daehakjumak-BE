package gdg.daejuju.daehakjumak.auth.repository.jpa;

import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, Long> {
    Optional<UserAuthEntity> findByKakaoId(Long kakaoId);

    @Modifying
    @Query("update UserAuthEntity ae set ae.refreshToken = null where ae.userId = :userId")
    void expireRefreshToken(@Param("userId") Long userId);

    @Modifying
    @Query("UPDATE UserAuthEntity ae SET ae.refreshToken = :refreshToken WHERE ae.userId = :userId")
    void updateRefreshToken(@Param("refreshToken") String refreshToken, @Param("userId") Long userId);

}
