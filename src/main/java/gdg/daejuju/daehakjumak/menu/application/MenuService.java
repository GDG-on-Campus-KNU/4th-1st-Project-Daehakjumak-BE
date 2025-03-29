package gdg.daejuju.daehakjumak.menu.application;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.menu.application.interfaces.MenuRepository;
import gdg.daejuju.daehakjumak.menu.domain.Menu;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.CreateMenuRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final JumakRepository jumakRepository;

    public Response<String> createMenu(CreateMenuRequestDto requestDto){

        Jumak jumak = jumakRepository.findById(requestDto.getJumak());
        Menu menu = new Menu(null, requestDto.getName(), requestDto.getDescription(), requestDto.getPrice(), requestDto.getImageUrl(), requestDto.getMenuType(), jumak);

        return Response.ok("Create Menu Success.");
    }
}
