package gdg.daejuju.daehakjumak.menu.domain;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.user.domain.User;
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
    private final Jumak jumak;

    public Menu(Long id, String name, String description, int price, String imageUrl, MenuType menuType, Jumak jumak) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.menuType = menuType;
        this.jumak = jumak;
    }
}
