package gdg.daejuju.daehakjumak.auth.application.interfaces;

import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.user.domain.User;

import java.util.Optional;

public interface UserAuthRepository {
    UserAuth registerUser(User user,Long kakaoId);

    Optional<UserAuth> findByUserId(Long userId);

    Optional<UserAuth> findByKakaoId(Long kakaoId);

    UserAuth loginUser(User user,Long kakaoId);

}
