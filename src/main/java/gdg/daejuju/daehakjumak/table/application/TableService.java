package gdg.daejuju.daehakjumak.table.application;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
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
    private final JumakRepository jumakRepository;

    // 테이블 생성
    @Transactional
    public Response<String> createTable(CreateTableRequestDto request){
        Jumak jumak = jumakRepository.findById(request.getJumak())
                .orElseThrow(() -> new IllegalArgumentException("해당 주막이 존재하지 않습니다.")).toJumak();
        Table table = new Table(null, request.getNumber(), request.getRow(), request.getColumn(), request.isActive(), jumak);

        tableRepository.save(new TableEntity(table));
        return Response.ok("테이블 생성 성공");
    }




}
