package gdg.daejuju.daehakjumak.waiting.repository;


import gdg.daejuju.daehakjumak.common.domain.exception.ErrorCode;
import gdg.daejuju.daehakjumak.common.domain.exception.NotFoundException;
import gdg.daejuju.daehakjumak.waiting.application.interfaces.WaitingRepository;
import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import gdg.daejuju.daehakjumak.waiting.domain.WaitingStatus;
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
        return jpaWaitingRepository.findById(id).orElseThrow(()->new NotFoundException(ErrorCode.NOT_FOUND));
    }

    @Override
    public List<Waiting> getWaitingList(Long jumakId) {
        return jpaWaitingRepository.findAllByJumak_Id(jumakId,WaitingStatus.WAITING).stream().map(WaitingEntity::toWaiting).toList();
        //toWaiting시 내부의 jumak을 사용해서 LAZY가 적용되도 추가 쿼리 나감 -> fetch join or EntityGraph사용해서 one query로 N+1 문제 해결
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

    @Override
    public boolean isAccesibleByUser(Long waitingId, String userId) {
        return jpaWaitingRepository.existsByIdAndUserId(waitingId, userId);
    }

    @Override
    public int getWaitingCount(Long jumakId) {
        return jpaWaitingRepository.getCountByJumak_Id(jumakId, WaitingStatus.WAITING);
    }

    @Override
    public int getMaxWaitingNumber(Long jumakId) {
        return jpaWaitingRepository.getMaxWaitingNumber(jumakId);
    }

    @Override
    public void completeWaitingStatus(Long waitingId) {
        jpaWaitingRepository.findById(waitingId).ifPresent(WaitingEntity::complete); //dirty checking
        /*jpaWaitingRepository.updateWaitingStatus(waitingId, WaitingStatus.COMPLETED);*/
    }
}
