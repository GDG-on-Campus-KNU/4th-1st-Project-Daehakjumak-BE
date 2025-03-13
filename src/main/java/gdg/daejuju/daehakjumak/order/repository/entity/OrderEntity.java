package gdg.daejuju.daehakjumak.order.repository.entity;

import gdg.daejuju.daehakjumak.order.domain.Order;
import gdg.daejuju.daehakjumak.order.domain.OrderStatus;
import gdg.daejuju.daehakjumak.table.repository.entity.TableEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jumak_order")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String menu;
    private String status;
    private int price;
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tableId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private TableEntity table;

    public OrderEntity(Order order){
        this.id = order.getId();
        this.menu = order.getMenu();
        this.status = order.getStatus().name();
        this.price = order.getPrice();
        this.quantity = order.getQuantity();
        this.table = new TableEntity(order.getTable());
    }
    public Order toOrder(){
        return Order.builder()
                .id(id)
                .menu(menu)
                .status(OrderStatus.valueOf(status))
                .price(price)
                .quantity(quantity)
                .table(table.toTable())
                .build();
    }
}
