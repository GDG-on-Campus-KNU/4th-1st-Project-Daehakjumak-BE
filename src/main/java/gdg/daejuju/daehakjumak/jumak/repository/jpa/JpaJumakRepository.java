package gdg.daejuju.daehakjumak.jumak.repository.jpa;

import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaJumakRepository extends JpaRepository<JumakEntity, Long> {

    @Modifying
    @Query("update JumakEntity je set je.jumakName = :jumakName where je.id = :id")
    void updateJumakName(@Param("id") Long id, @Param("jumakName") String jumakName);

    @Query("SELECT COUNT(je) > 0 FROM JumakEntity je WHERE je.id = :jumakId AND je.user.id = :userId")
    boolean existsByIdAndUserId(@Param("jumakId") Long jumakId, @Param("userId") String userId);
}
