package gdg.daejuju.daehakjumak.order.domain.dto.request;

import lombok.Data;

@Data
public class ModifyOrderRequestDto {
    private String menu;
    private String status;
    private int quantity;
}
