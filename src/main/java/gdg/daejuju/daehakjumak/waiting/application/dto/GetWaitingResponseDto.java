package gdg.daejuju.daehakjumak.waiting.application.dto;

import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import lombok.Getter;

@Getter
public class GetWaitingResponseDto {
    Long id;
    int nop;
    String phoneNum;

    public GetWaitingResponseDto(Waiting waiting) {
        this.id = waiting.getId();
        this.nop = waiting.getNop();
        this.phoneNum = waiting.getPhoneNum();
    }

}
