package gdg.daejuju.daehakjumak.auth.repository.jpa;

import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserAuthRepository extends JpaRepository<UserAuthEntity, Long> {
    Optional<UserAuthEntity> findByKakaoId(Long kakaoId);
}
