package gdg.daejuju.daehakjumak.table.domain;

import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class Table {

    private final Long id;
    private final Long number;
    private final int row;
    private final int column;
    private final boolean active;
    private final Jumak jumak;

    public Table(Long id, Long number, int row, int column, boolean active, Jumak jumak) {
        this.id = id;
        this.number = number;
        this.row = row;
        this.column = column;
        this.active = active;
        this.jumak = jumak;
    }
}
