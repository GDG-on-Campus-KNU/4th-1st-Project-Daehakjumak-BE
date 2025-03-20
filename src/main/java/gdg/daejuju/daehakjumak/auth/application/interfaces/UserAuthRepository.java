package gdg.daejuju.daehakjumak.auth.application.interfaces;

import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.auth.repository.entity.UserAuthEntity;
import gdg.daejuju.daehakjumak.user.domain.User;

import java.util.Optional;

public interface UserAuthRepository {
    UserAuthEntity registerUser(User user,Long kakaoId);

    Optional<UserAuth> findByUserId(Long userId);

    Optional<UserAuthEntity> findByKakaoId(Long kakaoId);

    UserAuth loginUser(UserAuthEntity userAuthEntity, String refreshToken);

    void logoutUser(Long UserId);

    boolean isRefreshTokenExpired(Long userId, String refreshToken);

    void updateRefreshToken(String refreshToken, Long userId);



}
