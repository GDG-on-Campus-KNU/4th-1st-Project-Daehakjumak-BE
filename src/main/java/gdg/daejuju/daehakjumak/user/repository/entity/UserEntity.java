package gdg.daejuju.daehakjumak.user.repository.entity;

import gdg.daejuju.daehakjumak.common.repository.TimeBaseEntity;
import gdg.daejuju.daehakjumak.jumak.repository.entity.JumakEntity;
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

    @OneToOne(mappedBy = "user",fetch = FetchType.LAZY)
    private JumakEntity jumak;

    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.kakaoId = user.getKakaoId();
        this.jumak = new JumakEntity(user.getJumak());
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .name(name)
                .kakaoId(kakaoId)
                .build();
    }
}
