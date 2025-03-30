package gdg.daejuju.daehakjumak.waiting.repository.jpa;

import gdg.daejuju.daehakjumak.waiting.repository.entity.WaitingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaWaitingRepository extends JpaRepository<WaitingEntity,Long> {
    @Query("SELECT w FROM WaitingEntity w JOIN FETCH w.jumak WHERE w.jumak.id = :jumakId")
    List<WaitingEntity> findAllByJumak_Id(@Param("jumakId") Long jumakId);

    @Query("SELECT COUNT(we) > 0 FROM WaitingEntity we JOIN JumakEntity je ON we.jumak.id = je.id " +
            "WHERE we.id = :waitingId AND je.user.id = :userId") //DB상에서는 JumakEntrity에 User객체가 아닌 user_id가 있으므로 jumak과 waiting 테이블 두개만 조인됨
    boolean existsByIdAndUserId(@Param("waitingId") Long waitingId, @Param("userId") String userId);
}
