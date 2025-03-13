package gdg.daejuju.daehakjumak.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private final Long id;
    private final String name;
    private final JumakInfo jumakInfo;

    public User(Long id, String name, JumakInfo jumakInfo) {
        this.id = id;
        this.name = name;
        this.jumakInfo = jumakInfo;
    }
}


