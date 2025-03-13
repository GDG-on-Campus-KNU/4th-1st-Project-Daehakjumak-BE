package gdg.daejuju.daehakjumak.jumak.repository.entity;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.domain.JumakInfo;
import gdg.daejuju.daehakjumak.user.repository.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jumak_jumak")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class JumakEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jumakName;
    private int maxRow;
    private int maxColumn;
    private int tableCount;
    private String qrLinkUrl;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT)) 
    private UserEntity user; //연관관계의 주인

    public JumakEntity(String jumakName, int maxRow, int maxColumn, int tableCount, String qrLinkUrl) {
        this.jumakName = jumakName;
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        this.tableCount = tableCount;
        this.qrLinkUrl = qrLinkUrl;
    }
    public JumakEntity(Jumak jumak){
        this.id= jumak.getId();
        this.jumakName=jumak.getJumakName();
        this.maxRow=jumak.getMaxRow();
        this.maxColumn= jumak.getMaxColumn();
        this.tableCount= jumak.getTableCount();
        this.qrLinkUrl=jumak.getQrLinkUrl();
    }
    public Jumak toJumak(){
        return Jumak.builder()
                .id(id)
                .jumakInfo(new JumakInfo(jumakName,maxRow,maxColumn,tableCount,qrLinkUrl))
                .build();
    }
}
