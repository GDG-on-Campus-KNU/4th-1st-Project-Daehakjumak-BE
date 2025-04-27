package gdg.daejuju.daehakjumak.table.ui;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.table.application.TableService;
import gdg.daejuju.daehakjumak.table.application.dto.request.CreateTableRequestDto;
import gdg.daejuju.daehakjumak.table.application.dto.request.ModifyTableStatusRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/table")
@RequiredArgsConstructor
public class TableController {

    private final TableService tableService;

    @PostMapping
    public Response<String> createTable(@RequestBody CreateTableRequestDto createTableRequestDto){
        return tableService.createTable(createTableRequestDto);
    }

    @DeleteMapping("{tableId}")
    public Response<String> deleteTable(@PathVariable Long tableId){
        return tableService.deleteTable(tableId);
    }

    @PutMapping("{tableId}")
    public Response<String> modifyStatus(@PathVariable Long tableId, @RequestBody ModifyTableStatusRequestDto requestDto){
        return tableService.modifyStatus(tableId, requestDto);
    }

}
