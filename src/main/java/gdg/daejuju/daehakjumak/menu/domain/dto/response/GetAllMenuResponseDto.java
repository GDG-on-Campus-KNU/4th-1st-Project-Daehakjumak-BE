package gdg.daejuju.daehakjumak.menu.domain.dto.response;

import gdg.daejuju.daehakjumak.menu.domain.MenuType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GetAllMenuResponseDto {
    private String name;
    private String description;
    private int price;
    private MenuType menuType;
    private String url;
}
