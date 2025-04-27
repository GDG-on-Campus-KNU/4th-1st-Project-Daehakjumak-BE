package gdg.daejuju.daehakjumak.jumak.domain;

import gdg.daejuju.daehakjumak.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Jumak {
    private Long id;
    private final JumakInfo jumakInfo;
    private User user;

    public Jumak(Long id, JumakInfo jumakInfo, User user) {
        this.id = id;
        this.jumakInfo = jumakInfo;
        this.user = user;
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
