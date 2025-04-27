package gdg.daejuju.daehakjumak.table.repository.entity;

import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="jumak_table")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private int rowNum;
    private int colNum;
    private boolean isActive;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="jumakId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private JumakEntity jumak;

    public TableEntity(gdg.daejuju.daehakjumak.table.domain.Table table){
        this.id = table.getId();
        this.number = table.getNumber();
        this.rowNum = table.getRow();
        this.colNum = table.getColumn();
        this.isActive = table.isActive();
        this.jumak = new JumakEntity(table.getJumak());
    }

    public gdg.daejuju.daehakjumak.table.domain.Table toTable(){
        return gdg.daejuju.daehakjumak.table.domain.Table.builder()
                .id(id)
                .number(number)
                .row(rowNum)
                .column(colNum)
                .active(isActive)
                .jumak(jumak.toJumak())
                .build();
    }
}
