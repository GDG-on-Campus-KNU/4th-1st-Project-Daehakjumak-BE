package gdg.daejuju.daehakjumak.purchaseHistory.application;

import gdg.daejuju.daehakjumak.purchaseHistory.repository.entity.PurchaseHistoryEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

@Service
public class PurchaseHistoryService {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<PurchaseHistoryEntity> purchaseHistoryTable;

    public PurchaseHistoryService(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.purchaseHistoryTable = dynamoDbEnhancedClient.table(
                "PurchaseHistory",
                TableSchema.fromBean(PurchaseHistoryEntity.class)
        );
    }

    public void savePurchaseHistory(PurchaseHistoryEntity purchaseHistory) {
        purchaseHistoryTable.putItem(purchaseHistory);
    }

    public PurchaseHistoryEntity getPurchaseHistory(String id) {
        return purchaseHistoryTable.getItem(r -> r.key(k -> k.partitionValue(id)));
    }

    public void deletePurchaseHistory(String id) {
        purchaseHistoryTable.deleteItem(r -> r.key(k -> k.partitionValue(id)));
    }
}
