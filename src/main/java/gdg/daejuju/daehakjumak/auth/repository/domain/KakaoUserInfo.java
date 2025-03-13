package gdg.daejuju.daehakjumak.auth.repository.domain;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.domain.JumakInfo;
import gdg.daejuju.daehakjumak.user.domain.User;

public class KakaoUserInfo {
    private Long id;
    private KakaoProperties properties;

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
                .jumak(new Jumak(null,new JumakInfo(null,5,5,0,null)))
                .build();
    }

    public String getNickname(){
        return properties.getNickname();
    }
}
