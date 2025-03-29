package gdg.daejuju.daehakjumak.menu.application.interfaces;

import gdg.daejuju.daehakjumak.menu.repository.entity.MenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {
}
