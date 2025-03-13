package gdg.daejuju.daehakjumak.menu.repository.entity;

import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import gdg.daejuju.daehakjumak.menu.domain.Menu;
import gdg.daejuju.daehakjumak.menu.domain.MenuType;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jumak_menu")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private int price;
    private String imageUrl;
    private String menuType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="jumakId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private JumakEntity jumakEntity;

    public MenuEntity(Menu menu){
        this.id = menu.getId();
        this.name = menu.getName();
        this.description = menu.getDescription();
        this.price = menu.getPrice();
        this.imageUrl = menu.getImageUrl();
        this.menuType = menu.getMenuType().name();
        this.jumakEntity = new JumakEntity(menu.getJumak());
    }

    public Menu toMenu(){
        return Menu.builder()
                .id(id)
                .name(name)
                .description(description)
                .price(price)
                .imageUrl(imageUrl)
                .menuType(MenuType.valueOf(menuType))
                .jumak(jumakEntity.toJumak())
                .build();
    }
}
