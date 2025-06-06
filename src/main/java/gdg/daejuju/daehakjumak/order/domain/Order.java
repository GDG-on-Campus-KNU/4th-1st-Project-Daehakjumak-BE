package gdg.daejuju.daehakjumak.order.domain;

import gdg.daejuju.daehakjumak.table.domain.Table;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Order {

    private final Long id;
    private final String menu;
    private final int quantity;
    private final int price;
    private final OrderStatus status;
    private final Table table;

    public Order(Long id, String menu, int quantity, int price, OrderStatus status, Table table) {
        this.id = id;
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
        this.status = status;
        this.table = table;
    }
}
