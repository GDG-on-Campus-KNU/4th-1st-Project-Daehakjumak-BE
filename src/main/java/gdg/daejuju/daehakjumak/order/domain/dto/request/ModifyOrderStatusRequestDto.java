package gdg.daejuju.daehakjumak.order.domain.dto.request;

import gdg.daejuju.daehakjumak.order.domain.OrderStatus;
import lombok.Data;

@Data
public class ModifyOrderStatusRequestDto {
    private OrderStatus status;
}
