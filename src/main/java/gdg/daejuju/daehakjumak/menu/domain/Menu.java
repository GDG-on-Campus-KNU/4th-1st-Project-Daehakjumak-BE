package gdg.daejuju.daehakjumak.menu.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Menu {
    private final Long id;
    private final String name;
    private final String description;
    private final int price;
    private final String imageUrl;
    private final MenuType menuType;

}
