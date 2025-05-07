package gdg.daejuju.daehakjumak.waiting.domain;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Waiting {
    private Long id;
    private int nop;
    private String phoneNum;
    private Jumak jumak;
    private int waitingNumber;
    private WaitingStatus status;

    @Builder
    public Waiting(Long id, int waitingNumber, int nop, String phoneNum, Jumak jumak) {
        this.id = id;
        this.waitingNumber = waitingNumber;
        this.nop = nop;
        this.phoneNum = phoneNum;
        this.jumak = jumak;
        this.status =WaitingStatus.WAITING;
    }
}
