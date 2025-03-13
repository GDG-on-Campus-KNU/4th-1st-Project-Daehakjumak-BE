package gdg.daejuju.daehakjumak.auth.repository.domain;

import gdg.daejuju.daehakjumak.user.domain.JumakInfo;
import gdg.daejuju.daehakjumak.user.domain.User;

public class KakaoUserInfo {
    private Long id;
    private KakaoProperties properties;

    // getters, setters

    public Long getId() {
        return id;
    }

    public KakaoProperties getProperties() {
        return properties;
    }

    public User toUser() {
        return User.builder()
                .name(getNickname())
                .kakaoId(id)
                .jumakInfo(new JumakInfo(null,5,5,0,null)).build(); // 기본 JumakInfo 생성 또는 필요에 따라 처리
    }

    public String getNickname(){
        return properties.getNickname();
    }
}
