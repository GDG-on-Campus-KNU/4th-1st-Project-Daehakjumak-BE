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
    private Jumak jumak;

    public User(Long id, String name, Jumak jumak) {
        this.id = id;
        this.name = name;
        this.jumak = jumak;
    }

}



