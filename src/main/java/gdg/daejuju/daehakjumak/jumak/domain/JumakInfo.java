package gdg.daejuju.daehakjumak.jumak.domain;

import lombok.Getter;

@Getter
public class JumakInfo {

    private final String jumakName;
    private final int maxRow;
    private final int maxColumn;
    private final int tableCount;
    private final String qrLinkUrl;

    public JumakInfo(String jumakName, int maxRow, int maxColumn, int tableCount, String qrLinkUrl) {
        this.jumakName = jumakName;
        this.maxRow = maxRow;
        this.maxColumn = maxColumn;
        this.tableCount = tableCount;
        this.qrLinkUrl = qrLinkUrl;
    }


}
