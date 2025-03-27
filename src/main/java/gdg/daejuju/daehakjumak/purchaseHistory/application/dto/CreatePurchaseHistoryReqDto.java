package gdg.daejuju.daehakjumak.purchaseHistory.application.dto;

import gdg.daejuju.daehakjumak.purchaseHistory.repository.entity.PurchaseHistoryEntity;
import lombok.Getter;

@Getter
public class CreatePurchaseHistoryReqDto {
    private String menu;
    private int quantity;
    private int price;
    private Long jumakId;

    public PurchaseHistoryEntity getEntity(){
        return new PurchaseHistoryEntity(menu,quantity,price,jumakId);
    }
}
