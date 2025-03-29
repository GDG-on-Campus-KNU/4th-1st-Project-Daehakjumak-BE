package gdg.daejuju.daehakjumak.menu.application;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.menu.application.interfaces.MenuRepository;
import gdg.daejuju.daehakjumak.menu.domain.Menu;
import gdg.daejuju.daehakjumak.menu.domain.MenuType;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.CreateMenuRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.ModifyMenuDescriptionRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.ModifyMenuNameRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.request.ModifyMenuPriceRequestDto;
import gdg.daejuju.daehakjumak.menu.domain.dto.response.GetAllMenuResponseDto;
import gdg.daejuju.daehakjumak.menu.repository.entity.MenuEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuService {

    private final MenuRepository menuRepository;
    private final JumakRepository jumakRepository;

    // 메뉴 생성
    @Transactional
    public Response<String> createMenu(CreateMenuRequestDto requestDto){

        Jumak jumak = jumakRepository.findById(requestDto.getJumak());
        Menu menu = new Menu(null, requestDto.getName(), requestDto.getDescription(), requestDto.getPrice(), null, requestDto.getMenuType(), jumak);

        MenuEntity menuEntity = new MenuEntity(menu);
        menuRepository.save(menuEntity);

        return Response.ok("Create Menu Success.");
    }

    // 메뉴 삭제
    @Transactional
    public Response<String> deleteMenu(Long menuId){

        menuRepository.deleteById(menuId);

        return Response.ok("Delete Menu Success.");
    }

    // 메뉴 이름 수정
    @Transactional
    public Response<String> modifyName(Long menuId, ModifyMenuNameRequestDto requestDto){
        MenuEntity menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴입니다."));

        menu.setName(requestDto.getName());

        return Response.ok("Modify menu name success.");
    }

    // 메뉴 가격 수정
    @Transactional
    public Response<String> modifyPrice(Long menuId, ModifyMenuPriceRequestDto requestDto){
        MenuEntity menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴입니다."));

        menu.setPrice(requestDto.getPrice());

        return Response.ok("Modify menu Price success.");
    }

    // 메뉴 설명 수정
    @Transactional
    public Response<String> modifyDescription(Long menuId, ModifyMenuDescriptionRequestDto requestDto){
        MenuEntity menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴입니다."));

        menu.setDescription(requestDto.getDescription());

        return Response.ok("Modify menu Description success.");
    }

    //모든 메뉴 불러오기
    @Transactional
    public Response<List<GetAllMenuResponseDto>> getAllMenu(Long jumakId){

        List<GetAllMenuResponseDto> list = menuRepository.findAllByJumakEntity_Id(jumakId)
                .stream()
                .map(menuEntity -> new GetAllMenuResponseDto(
                        menuEntity.getName(),
                        menuEntity.getDescription(),
                        menuEntity.getPrice(),
                        MenuType.valueOf(menuEntity.getMenuType())
                ))
                .collect(Collectors.toList());

        return Response.ok(list);
    }
}
