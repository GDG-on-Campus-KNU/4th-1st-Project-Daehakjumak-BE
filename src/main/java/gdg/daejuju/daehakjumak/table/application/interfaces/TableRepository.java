package gdg.daejuju.daehakjumak.table.application.interfaces;


import gdg.daejuju.daehakjumak.table.repository.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TableEntity, Long> {
}
