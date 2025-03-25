package gdg.daejuju.daehakjumak.auth.repository;

import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import gdg.daejuju.daehakjumak.auth.repository.jpa.JpaUserAuthRepository;
import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.user.domain.User;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
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
        UserEntity savedUser = userRepository.save(user);
        JumakEntity savedJumak = savedUser.getJumak(); //Cascade.ALL로 user persist 시 같이 persist일어났음
        savedJumak.setUser(savedUser); //dirty Checking (UserEntity와 JumakEntity 영속성 컨텍스트 내 관리되고 있는 상태)

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
    public boolean isRefreshTokenExpired(Long userId, String refreshToken) {
        UserAuth userAuth = findByUserId(userId).orElseThrow();
        return !userAuth.getRefreshToken().equals(refreshToken);
    }

    @Override
    public void updateRefreshToken(String refreshToken, Long userId) {
        jpaUserAuthRepository.updateRefreshToken(refreshToken,userId);
    }
}