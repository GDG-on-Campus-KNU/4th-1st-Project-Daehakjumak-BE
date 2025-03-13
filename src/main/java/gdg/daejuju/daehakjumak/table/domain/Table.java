package gdg.daejuju.daehakjumak.table.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Table {

    private final Long id;
    private final Long number;
    private final int row;
    private final int column;
    private final boolean isActive;

    public Table(Long id, Long number, int row, int column, boolean isActive) {
        this.id = id;
        this.number = number;
        this.row = row;
        this.column = column;
        this.isActive = isActive;
    }
}
