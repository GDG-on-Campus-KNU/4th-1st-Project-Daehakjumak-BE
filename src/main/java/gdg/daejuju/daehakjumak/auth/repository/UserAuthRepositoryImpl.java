package gdg.daejuju.daehakjumak.auth.repository;

import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import gdg.daejuju.daehakjumak.auth.repository.jpa.JpaUserAuthRepository;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(User user, Long kakaoId) {
        User savedUser = userRepository.save(user);
        UserAuthEntity savedUserEntity = jpaUserAuthRepository.save(new UserAuthEntity(savedUser.getId(), kakaoId)); //Identity전략으로 인해 쓰기 지연은 안됨
        return savedUserEntity.toUserAuth();
    }

    @Override
    public Optional<UserAuth> findByKakaoId(Long kakaoId) {
        return jpaUserAuthRepository.findByKakaoId(kakaoId).map(UserAuthEntity::toUserAuth);
    }

    @Override
    public Optional<UserAuth> findByUserId(Long userId) {
        return jpaUserAuthRepository.findById(userId).map(UserAuthEntity::toUserAuth);
    }

    /* @Override
    @Transactional
    public UserAuth loginUser(String email, String password,String fcmToken) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findByEmail(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();
        if (!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        userAuthEntity.updateLastLoginAt(); //dirty checking
        jpaFcmTokenRepository.save(new FcmTokenEntity(userAuth.getUserId(),fcmToken));
        return userAuth;
    }*/
}