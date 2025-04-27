package gdg.daejuju.daehakjumak.purchaseHistory.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.purchaseHistory.application.SalesService;
import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.MenuSalesResponseDto;
import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.TotalSalesRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;


    @PostMapping("/total")
    public Response<Long> getTotalSales(@RequestBody TotalSalesRequestDto dto) {
        long totalSales = salesService.calculateTotalSalesByPeriod(dto);
        return Response.ok(totalSales);
    }

    @GetMapping("/menu-total")
    public Response<List<MenuSalesResponseDto>> getMenuSales(@RequestParam("jumakId") Long jumakId) {
        List<MenuSalesResponseDto> menuSales = salesService.calculateMenuSales(jumakId);
        return Response.ok(menuSales);
    }
}
