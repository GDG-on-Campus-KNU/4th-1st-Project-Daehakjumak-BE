package gdg.daejuju.daehakjumak.user.repository;

import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.user.domain.User;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import gdg.daejuju.daehakjumak.user.repository.jpa.JpaUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    @Override
    public User save(User user) {
        UserEntity userEntity = new UserEntity(user);
        UserEntity savedEntity = jpaUserRepository.save(userEntity);
        return savedEntity.toUser();
    }


}
