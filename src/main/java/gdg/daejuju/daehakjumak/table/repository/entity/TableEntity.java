package gdg.daejuju.daehakjumak.table.repository.entity;

import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jumak_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private int row;
    private int column;
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity user;

    public TableEntity(gdg.daejuju.daehakjumak.table.domain.Table table){
        this.id = table.getId();
        this.number = table.getNumber();
        this.row = table.getRow();
        this.column = table.getColumn();
        this.isActive = table.isActive();
        this.user = new UserEntity(table.getUser());
    }

    public gdg.daejuju.daehakjumak.table.domain.Table toTable(){
        return gdg.daejuju.daehakjumak.table.domain.Table.builder()
                .id(id)
                .number(number)
                .row(row)
                .column(column)
                .isActive(isActive)
                .user(user.toUser())
                .build();
    }
}
