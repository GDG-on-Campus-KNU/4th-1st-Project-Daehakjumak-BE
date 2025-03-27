package gdg.daejuju.daehakjumak.purchaseHistory.repository.entity;

import gdg.daejuju.daehakjumak.common.repository.TimeBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

@DynamoDbBean
@Getter
@Setter
@NoArgsConstructor
public class PurchaseHistoryEntity {

    private String id;
    private String menu;
    private int quantity;
    private int price;
    private Long jumakId;
    private LocalDateTime regDt;

    public PurchaseHistoryEntity(String menu, int quantity, int price, Long jumakId) {
        this.id = UUID.randomUUID().toString();
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
        this.jumakId = jumakId;
        this.regDt = LocalDateTime.now();
    }

    @DynamoDbPartitionKey
    public Long getJumakId(){
        return jumakId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("regDt")
    public Long getRegDt() { // Epoch Time으로 변환된 regDt 반환
        return regDt.toEpochSecond(ZoneOffset.UTC);
    }

    public void setRegDt(Long epochTime) { // DynamoDB에서 불러올 때
        this.regDt = LocalDateTime.ofEpochSecond(epochTime, 0, ZoneOffset.UTC);
    }

    public LocalDateTime getOriginRegDt() {
        return regDt;
    }

    public void setOriginRegDt(LocalDateTime regDt) {
        this.regDt = regDt;
    }

    public long getTotalPrice(){
        return (long) price * quantity;
    }
}