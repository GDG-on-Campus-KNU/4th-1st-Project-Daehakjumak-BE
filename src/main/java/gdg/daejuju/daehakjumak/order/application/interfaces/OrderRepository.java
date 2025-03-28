package gdg.daejuju.daehakjumak.order.application.interfaces;

import gdg.daejuju.daehakjumak.order.repository.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
