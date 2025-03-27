package gdg.daejuju.daehakjumak.purchaseHistory.application.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class TotalSalesRequestDto {
    @NotNull(message = "주막 ID는 필수입니다")
    private Long jumakId;

    @NotNull(message = "시작 날짜는 필수입니다")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime startDate;

    @NotNull(message = "종료 날짜는 필수입니다")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime endDate;
}
