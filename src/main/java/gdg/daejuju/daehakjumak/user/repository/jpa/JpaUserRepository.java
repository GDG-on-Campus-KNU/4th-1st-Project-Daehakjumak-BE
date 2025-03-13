package gdg.daejuju.daehakjumak.user.repository.jpa;

import gdg.daejuju.daehakjumak.user.domain.User;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByKakaoId(Long kakaoId);
}
