package gdg.daejuju.daehakjumak.table.application;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.repository.JumakRepositoryImpl;
import gdg.daejuju.daehakjumak.table.application.dto.request.ModifyTableStatusRequestDto;
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

    // 테이블 활성화 상태 수정
    @Transactional
    public Response<String> modifyStatus(Long tableId, ModifyTableStatusRequestDto requestDto){
        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 테이블 번호입니다."));

        table.setActive(requestDto.isStatus());

        return Response.ok("Modify Table Status");
    }




}
