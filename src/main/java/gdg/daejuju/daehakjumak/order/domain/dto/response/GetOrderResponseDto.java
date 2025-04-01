package gdg.daejuju.daehakjumak.order.domain.dto.response;

import gdg.daejuju.daehakjumak.order.domain.OrderStatus;
import gdg.daejuju.daehakjumak.table.domain.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetOrderResponseDto {
    private String menu;
    private int quantity;
    private int price;
    private OrderStatus status;
    private Long tableId;
}
