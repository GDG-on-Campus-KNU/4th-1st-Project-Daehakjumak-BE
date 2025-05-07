package gdg.daejuju.daehakjumak.waiting.repository.entity;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="jumak_waiting")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WaitingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int nop;
    private String phoneNum;
    private int waitingNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="jumakId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private JumakEntity jumak;

    public WaitingEntity(Waiting waiting){
        this.nop =waiting.getNop();
        this.phoneNum = waiting.getPhoneNum();
        this.jumak = new JumakEntity(waiting.getJumak());
        this.waitingNumber = waiting.getWaitingNumber();
    }

    public Waiting toWaiting(){
        return Waiting.builder()
                .id(id)
                .nop(nop)
                .phoneNum(phoneNum)
                .jumak(jumak.toJumak())
                .build();
    }

    public void updateNop(int nop){
        this.nop = nop;
    }
    public void updatePhoneNum(String phoneNum){
        this.phoneNum = phoneNum;
    }
}
