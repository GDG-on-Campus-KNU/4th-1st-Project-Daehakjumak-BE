package gdg.daejuju.daehakjumak.menu.application;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.menu.application.interfaces.MenuRepository;
import gdg.daejuju.daehakjumak.menu.domain.Menu;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.CreateMenuRequestDto;
import gdg.daejuju.daehakjumak.menu.repository.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final JumakRepository jumakRepository;

    // 메뉴 생성
    public Response<String> createMenu(CreateMenuRequestDto requestDto){

        Jumak jumak = jumakRepository.findById(requestDto.getJumak());
        Menu menu = new Menu(null, requestDto.getName(), requestDto.getDescription(), requestDto.getPrice(), null, requestDto.getMenuType(), jumak);

        MenuEntity menuEntity = new MenuEntity(menu);
        menuRepository.save(menuEntity);

        return Response.ok("Create Menu Success.");
    }

    // 메뉴 삭제
    public Response<String> deleteMenu(Long menuId){

        menuRepository.deleteById(menuId);

        return Response.ok("Delete Menu Success.");
    }
}
