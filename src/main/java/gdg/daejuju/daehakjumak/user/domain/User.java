package gdg.daejuju.daehakjumak.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class User {

    private final Long id;
    private final String name;
    private final JumakInfo jumakInfo;
    private final Long kakaoId;

    public User(Long id, String name, JumakInfo jumakInfo, Long kakaoId) {
        this.id = id;
        this.name = name;
        this.jumakInfo = jumakInfo;
        this.kakaoId = kakaoId;
    }

    public String getJumakName(){
        return jumakInfo.getJumakName();
    }

    public int getMaxRow(){
        return jumakInfo.getMaxRow();
    }

    public int getMaxColumn() {
        return jumakInfo.getMaxColumn();
    }

    public int getTableCount() {
        return jumakInfo.getTableCount();
    }

    public String getQrLinkUrl() {
        return jumakInfo.getQrLinkUrl();
    }

}



