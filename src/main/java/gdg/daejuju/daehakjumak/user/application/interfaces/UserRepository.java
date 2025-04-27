package gdg.daejuju.daehakjumak.user.application.interfaces;

import gdg.daejuju.daehakjumak.user.domain.User;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {
    UserEntity save(User user);

    User findById(Long userId);

}
