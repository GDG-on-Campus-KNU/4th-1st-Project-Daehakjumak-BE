package gdg.daejuju.daehakjumak.auth.repository;

import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import gdg.daejuju.daehakjumak.auth.repository.jpa.JpaUserAuthRepository;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    public UserAuthEntity registerUser(User user, Long kakaoId) {
        User savedUser = userRepository.save(user);
        return jpaUserAuthRepository.save(new UserAuthEntity(savedUser.getId(), kakaoId,null));
    }

    @Override
    public UserAuth loginUser(UserAuthEntity userAuthEntity, String refreshToken){
        userAuthEntity.updateLastLoginAt(); //dirty checking
        userAuthEntity.updateRefreshToken(refreshToken); //dirty checking
        return userAuthEntity.toUserAuth();
    }

    @Override
    public Optional<UserAuthEntity> findByKakaoId(Long kakaoId) {
        return jpaUserAuthRepository.findByKakaoId(kakaoId);
    }

    @Override
    public Optional<UserAuth> findByUserId(Long userId) {
        return jpaUserAuthRepository.findById(userId).map(UserAuthEntity::toUserAuth);
    }

    @Override
    public void logoutUser(Long userId){
        jpaUserAuthRepository.expireRefreshToken(userId);
    }

    @Override
    public boolean checkRefreshTokenExpired(Long userId, String refreshToken) {
        UserAuth userAuth = findByUserId(userId).orElseThrow();
        return userAuth.getRefreshToken().equals(refreshToken);
    }
}