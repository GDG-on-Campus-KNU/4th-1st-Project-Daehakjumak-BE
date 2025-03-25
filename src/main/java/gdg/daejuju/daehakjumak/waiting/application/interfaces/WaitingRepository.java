package gdg.daejuju.daehakjumak.waiting.application.interfaces;

import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import gdg.daejuju.daehakjumak.waiting.repository.entity.WaitingEntity;

import java.util.List;

public interface WaitingRepository {
    Long save(Waiting waiting);
    WaitingEntity findById(Long id);
    void delete(Long id);
    List<Waiting> getWaitingList(Long jumakId);

    void updateNop(Long id, int nop);
    void updatePhoneNum(Long id, String phoneNum);
}
