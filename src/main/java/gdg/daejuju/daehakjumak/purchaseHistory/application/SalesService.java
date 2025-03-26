package gdg.daejuju.daehakjumak.purchaseHistory.application;

import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.MenuSalesResponseDto;
import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.TotalSalesRequestDto;
import gdg.daejuju.daehakjumak.purchaseHistory.repository.entity.PurchaseHistoryEntity;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryEnhancedRequest;

import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesService {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private final DynamoDbTable<PurchaseHistoryEntity> purchaseHistoryTable;

    public SalesService(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        this.dynamoDbEnhancedClient = dynamoDbEnhancedClient;
        this.purchaseHistoryTable = dynamoDbEnhancedClient.table(
                "PurchaseHistory",
                TableSchema.fromBean(PurchaseHistoryEntity.class)
        );
    }

    public long calculateTotalSalesByPeriod(TotalSalesRequestDto dto) {
        Long jumakId = dto.getJumakId();
        long startEpoch = dto.getStartDate().toEpochSecond(ZoneOffset.UTC);
        long endEpoch = dto.getEndDate().toEpochSecond(ZoneOffset.UTC);

        QueryConditional queryConditional = QueryConditional.sortBetween(
                Key.builder().partitionValue(jumakId).sortValue(startEpoch).build(),
                Key.builder().partitionValue(jumakId).sortValue(endEpoch).build()
        );

        QueryEnhancedRequest request = QueryEnhancedRequest.builder()
                .queryConditional(queryConditional)
                .build();

        return purchaseHistoryTable.query(request)
                .items()
                .stream()
                .mapToLong(PurchaseHistoryEntity::getTotalPrice)
                .sum();
    }

    public List<MenuSalesResponseDto> calculateMenuSales(Long jumakId) {
        QueryConditional queryConditional = QueryConditional.keyEqualTo(
                Key.builder().partitionValue(jumakId).build()
        );

        QueryEnhancedRequest request = QueryEnhancedRequest.builder()
                .queryConditional(queryConditional)
                .build();

        return purchaseHistoryTable.query(request)
                .items()
                .stream()
                .collect(Collectors.groupingBy(
                        PurchaseHistoryEntity::getMenu,
                        Collectors.summingLong(PurchaseHistoryEntity::getTotalPrice)
                ))
                .entrySet()
                .stream()
                .map(entry -> new MenuSalesResponseDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}