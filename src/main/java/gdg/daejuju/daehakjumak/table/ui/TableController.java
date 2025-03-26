package gdg.daejuju.daehakjumak.table.ui;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.table.application.TableService;
import gdg.daejuju.daehakjumak.table.application.dto.request.CreateTableRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping
    public Response<String> createTable(@RequestBody CreateTableRequestDto createTableRequestDto){
        return tableService.createTable(createTableRequestDto);
    }
}
