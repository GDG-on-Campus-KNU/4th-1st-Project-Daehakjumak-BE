package gdg.daejuju.daehakjumak.order.domain.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequestDto {

    private Long jumakId;
    private String menu;
    private int price;
    private int quantity;
}
