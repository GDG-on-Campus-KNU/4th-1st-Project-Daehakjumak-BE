package gdg.daejuju.daehakjumak.waiting.application;

import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.waiting.application.dto.CreateWaitingRequestDto;
import gdg.daejuju.daehakjumak.waiting.application.dto.GetWaitingResponseDto;
import gdg.daejuju.daehakjumak.waiting.application.interfaces.WaitingRepository;
import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WaitingService {

    private final WaitingRepository waitingRepository;
    private final JumakRepository jumakRepository;

    @Transactional
    public void updateNop(Long waitingId, int nop) {
        waitingRepository.updateNop(waitingId, nop);

    }
    @Transactional
    public void updatePhoneNum(Long waitingId, String phoneNum) {
        waitingRepository.updatePhoneNum(waitingId,phoneNum);
    }

    @Transactional
    public Long createWaiting(CreateWaitingRequestDto dto) {
        Jumak jumak = jumakRepository.findById(dto.jumakId());
        int maxWaitingNumber = waitingRepository.getMaxWaitingNumber(dto.jumakId());
        return waitingRepository.save(new Waiting(null,maxWaitingNumber+1,dto.nop(),dto.phoneNum(),jumak));
    }

    @Transactional
    public void completeWaiting(Long waitingId) {
        waitingRepository.completeWaitingStatus(waitingId);
    }

    public List<GetWaitingResponseDto> getWaitingList(Long jumakId) {
        return waitingRepository.getWaitingList(jumakId).stream().map(GetWaitingResponseDto::new).toList();
    }

    public int getWaitingCount(Long jumakId){
        return waitingRepository.getWaitingCount(jumakId);
    }

}
