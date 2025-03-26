package gdg.daejuju.daehakjumak.waiting.repository;


import gdg.daejuju.daehakjumak.waiting.application.interfaces.WaitingRepository;
import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import gdg.daejuju.daehakjumak.waiting.repository.entity.WaitingEntity;
import gdg.daejuju.daehakjumak.waiting.repository.jpa.JpaWaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WaitingRepositoryImpl implements WaitingRepository {

    private final JpaWaitingRepository jpaWaitingRepository;

    @Override
    public Long save(Waiting waiting) {
        WaitingEntity savedWaitingEntity = jpaWaitingRepository.save(new WaitingEntity(waiting));
        return savedWaitingEntity.getId();
    }

    @Override
    public WaitingEntity findById(Long id) {
        return jpaWaitingRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        jpaWaitingRepository.deleteById(id);
    }

    @Override
    public List<Waiting> getWaitingList(Long jumakId) {
        return jpaWaitingRepository.findAllByJumak_Id(jumakId).stream().map(WaitingEntity::toWaiting).toList();
    }

    @Override
    public void updateNop(Long id,int nop){
        WaitingEntity findWaiting = findById(id);
        findWaiting.updateNop(nop);
    }

    @Override
    public void updatePhoneNum(Long id,String phoneNum){
        WaitingEntity findWaiting = findById(id);
        findWaiting.updatePhoneNum(phoneNum);
    }


}
