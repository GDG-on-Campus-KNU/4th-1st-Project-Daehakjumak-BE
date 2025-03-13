package gdg.daejuju.daehakjumak.user.repository.entity;

import gdg.daejuju.daehakjumak.common.repository.TimeBaseEntity;
import gdg.daejuju.daehakjumak.user.domain.JumakInfo;
import gdg.daejuju.daehakjumak.user.domain.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Entity
@Table(name="jumak_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate //필드 중 변경된 값만 update퀴리에 사용, 불필요하게 모든 필드값 업데이트 하지 않도록 하기 위함
public class UserEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long kakaoId;
    private String jumakName;
    private int maxRow;
    private int maxColumn;
    private int tableCount;
    private String qrLinkUrl;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.kakaoId = user.getKakaoId();
        this.jumakName = user.getJumakName();
        this.maxRow = user.getMaxRow();
        this.maxColumn = user.getMaxColumn();
        this.tableCount = user.getTableCount();
        this.qrLinkUrl = user.getQrLinkUrl();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .name(name)
                .kakaoId(kakaoId)
                .jumakInfo(new JumakInfo(jumakName,maxRow,maxColumn,tableCount,qrLinkUrl))
                .build();
    }
}
