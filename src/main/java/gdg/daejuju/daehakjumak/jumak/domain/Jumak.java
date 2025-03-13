package gdg.daejuju.daehakjumak.jumak.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Jumak {
    private Long id;
    private final JumakInfo jumakInfo;

    public Jumak(Long id, JumakInfo jumakInfo) {
        this.id = id;
        this.jumakInfo = jumakInfo;
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
