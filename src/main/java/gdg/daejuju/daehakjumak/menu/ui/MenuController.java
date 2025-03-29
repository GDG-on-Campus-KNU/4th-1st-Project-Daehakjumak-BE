package gdg.daejuju.daehakjumak.menu.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.menu.application.MenuService;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.CreateMenuRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.ModifyMenuDescriptionRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.ModifyMenuNameRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.ModifyMenuPriceRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.response.GetAllMenuResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{menuId}/name")
    public Response<String> modifyName(@PathVariable Long menuId, @RequestBody ModifyMenuNameRequestDto requestDto){
        return menuService.modifyName(menuId, requestDto);
    }

    @PutMapping("/{menuId}/price")
    public Response<String> modifyPrice(@PathVariable Long menuId, @RequestBody ModifyMenuPriceRequestDto requestDto){
        return menuService.modifyPrice(menuId, requestDto);
    }

    @PutMapping("/{menuId}/description")
    public Response<String> modifyDescription(@PathVariable Long menuId, @RequestBody ModifyMenuDescriptionRequestDto requestDto){
        return menuService.modifyDescription(menuId, requestDto);
    }

    @GetMapping("/{jumakId}")
    public Response<List<GetAllMenuResponseDto>> getAllMenu(@PathVariable Long jumakId){
        return menuService.getAllMenu(jumakId);
    }
}
