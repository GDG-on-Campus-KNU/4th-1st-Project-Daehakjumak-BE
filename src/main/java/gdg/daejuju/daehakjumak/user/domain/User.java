package gdg.daejuju.daehakjumak.user.domain;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.domain.JumakInfo;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private final Long id;
    private final String name;
    private final Jumak jumak;
    private final Long kakaoId;

    public User(Long id, String name, Jumak jumak, Long kakaoId) {
        this.id = id;
        this.name = name;
        this.jumak = jumak;
        this.kakaoId = kakaoId;
    }
}



