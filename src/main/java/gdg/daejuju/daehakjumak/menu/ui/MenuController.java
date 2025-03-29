package gdg.daejuju.daehakjumak.menu.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.menu.application.MenuService;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.CreateMenuRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public Response<String> createMenu(@RequestBody CreateMenuRequestDto requestDto){
        return menuService.createMenu(requestDto);
    }
}
