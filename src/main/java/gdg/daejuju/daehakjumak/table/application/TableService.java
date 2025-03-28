package gdg.daejuju.daehakjumak.table.application;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.repository.JumakRepositoryImpl;
import gdg.daejuju.daehakjumak.table.application.interfaces.TableRepository;
import gdg.daejuju.daehakjumak.table.domain.Table;
import gdg.daejuju.daehakjumak.table.application.dto.request.CreateTableRequestDto;
import gdg.daejuju.daehakjumak.table.repository.entity.TableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TableService {

    private final TableRepository tableRepository;
    private final JumakRepositoryImpl jumakRepository;

    // 테이블 생성
    @Transactional
    public Response<String> createTable(CreateTableRequestDto request){
        Jumak jumak = jumakRepository.findById(request.getJumak());
        Table table = new Table(null, request.getNumber(), request.getRow(), request.getColumn(), request.isActive(), jumak);

        tableRepository.save(new TableEntity(table));
        return Response.ok("Create Table success");
    }

    // 테이블 삭제
    @Transactional
    public Response<String> deleteTable(Long tableId){
        tableRepository.deleteById(tableId);

        return Response.ok("Delete Table success");
    }




}
