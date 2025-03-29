package gdg.daejuju.daehakjumak.menu.domain.dto.request;

import gdg.daejuju.daehakjumak.menu.domain.MenuType;
import lombok.Data;

@Data
public class CreateMenuRequestDto {
    private String name;
    private String description;
    private int price;
    private MenuType menuType;
    private Long jumak;
}
