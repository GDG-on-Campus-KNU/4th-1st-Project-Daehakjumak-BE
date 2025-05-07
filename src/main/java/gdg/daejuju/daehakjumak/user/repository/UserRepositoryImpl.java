package gdg.daejuju.daehakjumak.user.repository;

import gdg.daejuju.daehakjumak.common.domain.exception.ErrorCode;
import gdg.daejuju.daehakjumak.common.domain.exception.NotFoundException;
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
    public UserEntity save(User user) {
        UserEntity userEntity = new UserEntity(user);
        return jpaUserRepository.save(userEntity);
    }

    @Override
    public User findById(Long userId) {
        return jpaUserRepository.findById(userId).map(UserEntity::toUser).orElseThrow(()->new NotFoundException(ErrorCode.NOT_FOUND));
    }
}
