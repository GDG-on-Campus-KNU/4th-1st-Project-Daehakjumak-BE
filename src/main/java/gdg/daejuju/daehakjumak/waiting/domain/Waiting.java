package gdg.daejuju.daehakjumak.waiting.domain;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Waiting {
    private Long id;
    private int nop;
    private int phoneNum;
    private Jumak jumak;

    public Waiting(Long id, int nop, int phoneNum, Jumak jumak) {
        this.id = id;
        this.nop = nop;
        this.phoneNum = phoneNum;
        this.jumak = jumak;
    }
}
