package gdg.daejuju.daehakjumak.user.repository.jpa;

import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<UserEntity, Long> {
}
