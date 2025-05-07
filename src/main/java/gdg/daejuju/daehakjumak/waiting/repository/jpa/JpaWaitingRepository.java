package gdg.daejuju.daehakjumak.waiting.repository.jpa;

import gdg.daejuju.daehakjumak.waiting.domain.WaitingStatus;
import gdg.daejuju.daehakjumak.waiting.repository.entity.WaitingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaWaitingRepository extends JpaRepository<WaitingEntity,Long> {
    @Query("SELECT we FROM WaitingEntity we JOIN FETCH we.jumak WHERE we.jumak.id = :jumakId and we.status = :status")
    List<WaitingEntity> findAllByJumak_Id(@Param("jumakId") Long jumakId, @Param("status") WaitingStatus status);

    @Query("SELECT COUNT(we) > 0 FROM WaitingEntity we JOIN JumakEntity je ON we.jumak.id = je.id " +
            "WHERE we.id = :waitingId AND je.user.id = :userId") //DB상에서는 JumakEntity에 User객체가 아닌 user_id가 있으므로 jumak과 waiting 테이블 두개만 조인됨
    boolean existsByIdAndUserId(@Param("waitingId") Long waitingId, @Param("userId") String userId);

    @Query("SELECT COUNT(we) FROM WaitingEntity we WHERE we.jumak.id = :jumakId and we.status = :status")
    int getCountByJumak_Id(Long jumakId, @Param("status") WaitingStatus status);

    @Query("SELECT COALESCE(MAX(we.waitingNumber), 0) FROM WaitingEntity we WHERE we.jumak.id = :jumakId")
    int getMaxWaitingNumber(Long jumakId);

    @Query("UPDATE WaitingEntity we SET we.status = :status WHERE we.id = :waitingId")
    void updateWaitingStatus(@Param("waitingId") Long waitingId, @Param("status") WaitingStatus status);
}
