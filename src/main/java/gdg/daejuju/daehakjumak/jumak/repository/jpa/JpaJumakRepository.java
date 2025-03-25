package gdg.daejuju.daehakjumak.jumak.repository.jpa;

import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaJumakRepository extends JpaRepository<JumakEntity, Long> {

}
