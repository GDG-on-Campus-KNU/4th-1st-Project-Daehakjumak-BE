package gdg.daejuju.daehakjumak.waiting.application.dto;

import lombok.Getter;

@Getter
public class GetWaitingCountResDto {
    int currentWaitingCount;

    public GetWaitingCountResDto(int currentWaitingCount) {
        this.currentWaitingCount = currentWaitingCount;
    }
}
