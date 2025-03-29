package gdg.daejuju.daehakjumak.menu.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.menu.application.MenuService;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.CreateMenuRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/menu")
@RequiredArgsConstructor
public class MenuController {

    private final MenuService menuService;

    @PostMapping
    public Response<String> createMenu(@RequestBody CreateMenuRequestDto requestDto){
        return menuService.createMenu(requestDto);
    }

    @DeleteMapping("/{menuId}")
    public Response<String> deleteMenu(@PathVariable Long menuId){
        return menuService.deleteMenu(menuId);
    }
}
