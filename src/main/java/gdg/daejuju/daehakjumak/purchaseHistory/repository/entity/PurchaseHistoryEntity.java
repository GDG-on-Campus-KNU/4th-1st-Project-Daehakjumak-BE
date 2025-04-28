package gdg.daejuju.daehakjumak.purchaseHistory.repository.entity;

import gdg.daejuju.daehakjumak.common.repository.TimeBaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
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

    // 한국 시간대 상수 정의
    private static final ZoneId KOREA_ZONE = ZoneId.of("Asia/Seoul");

    public PurchaseHistoryEntity(String menu, int quantity, int price, Long jumakId) {
        this.id = UUID.randomUUID().toString();
        this.menu = menu;
        this.quantity = quantity;
        this.price = price;
        this.jumakId = jumakId;
        this.regDt = LocalDateTime.now(KOREA_ZONE);
    }

    @DynamoDbPartitionKey
    public Long getJumakId(){
        return jumakId;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("regDt")
    public Long getRegDt() {
        // 한국 시간대 기준으로 Epoch Time 변환
        return regDt.atZone(KOREA_ZONE).toEpochSecond();
    }

    public void setRegDt(Long epochTime) {
        // DynamoDB에서 불러올 때 epoch 시간을 한국 시간대의 LocalDateTime으로 변환
        this.regDt = LocalDateTime.ofInstant(Instant.ofEpochSecond(epochTime), KOREA_ZONE);
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