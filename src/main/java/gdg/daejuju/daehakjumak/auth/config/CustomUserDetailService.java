package gdg.daejuju.daehakjumak.auth.config;

import gdg.daejuju.daehakjumak.auth.application.interfaces.UserAuthRepository;
import gdg.daejuju.daehakjumak.auth.repository.domain.UserAuth;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        // userId로 인증 정보 조회
        UserAuth userAuth = userAuthRepository.findByUserId(Long.parseLong(userId))
                .orElseThrow(() -> new UsernameNotFoundException("사용자 인증 정보를 찾을 수 없습니다: " + userId));

        // 권한이 필요 없으므로 빈 권한 목록 생성
        List<GrantedAuthority> authorities = Collections.emptyList();

        // Spring Security의 User 객체 생성 (비밀번호는 소셜 로그인이므로 빈 문자열)
        return new User(
                userAuth.getUserId().toString(),
                "", // 소셜 로그인이므로 비밀번호 불필요
                authorities
        );
    }
}