package gdg.daejuju.daehakjumak.purchaseHistory.ui;

import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.purchaseHistory.application.PurchaseHistoryService;
import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.CreatePurchaseHistoryReqDto;
import gdg.daejuju.daehakjumak.purchaseHistory.repository.entity.PurchaseHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchaseHistory")
public class PurchaseHistoryController {
    private final PurchaseHistoryService purchaseHistoryService;

    @PostMapping
    public Response<String> createPurchaseHistory(@RequestBody CreatePurchaseHistoryReqDto dto){
        String id = purchaseHistoryService.savePurchaseHistory(dto);
        return Response.ok(id);
    }

}
