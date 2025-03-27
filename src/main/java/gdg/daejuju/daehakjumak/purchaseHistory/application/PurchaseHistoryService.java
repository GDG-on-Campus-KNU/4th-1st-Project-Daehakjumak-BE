package gdg.daejuju.daehakjumak.purchaseHistory.application;

import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.CreatePurchaseHistoryReqDto;
import gdg.daejuju.daehakjumak.purchaseHistory.repository.entity.PurchaseHistoryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

@Service
@RequiredArgsConstructor
public class PurchaseHistoryService {
    private final DynamoDbTable<PurchaseHistoryEntity> purchaseHistoryTable;


    public String savePurchaseHistory(CreatePurchaseHistoryReqDto dto) {
        PurchaseHistoryEntity entity = dto.getEntity();
        purchaseHistoryTable.putItem(entity);
        return entity.getId();
    }

    public PurchaseHistoryEntity getPurchaseHistory(Long jumakId, Long regDt) {
        return purchaseHistoryTable.getItem(r -> r
                .key(k -> k
                        .partitionValue(jumakId)
                        .sortValue(regDt)
                )
        );
    }
}
