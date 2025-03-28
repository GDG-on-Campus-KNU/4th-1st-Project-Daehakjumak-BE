package gdg.daejuju.daehakjumak.table.application.dto.request;

import lombok.Data;

@Data
public class CreateTableRequestDto {
    private final Long number;
    private final int row;
    private final int column;
    private final boolean active;
    private final Long jumak;

}
