package gdg.daejuju.daehakjumak.waiting.repository.jpa;

import gdg.daejuju.daehakjumak.waiting.repository.entity.WaitingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaWaitingRepository extends JpaRepository<WaitingEntity,Long> {
    List<WaitingEntity> findAllByJumakId(Long jumakId);
}
