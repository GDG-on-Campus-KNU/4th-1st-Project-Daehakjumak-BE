package gdg.daejuju.daehakjumak.auth.application.interfaces;

import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.user.domain.User;

public interface UserAuthRepository {
    User registerUser(User user);

}
