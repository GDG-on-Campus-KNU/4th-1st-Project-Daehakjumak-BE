package gdg.daejuju.daehakjumak.order.ui;



import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.order.application.OrderService;
import gdg.daejuju.daehakjumak.order.domain.dto.request.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 테이블에 주문 추가
    @PostMapping("{tableId}")
    public Response<String> createOrder(@PathVariable Long tableId, @RequestBody CreateOrderRequestDto requestDto){
        return orderService.createOrder(tableId, requestDto);
    }

    // 테이블 주문 삭제
    @DeleteMapping("{orderId}")
    public Response<String> deleteOrder(@PathVariable Long orderId){
        return orderService.deleteOrder(orderId);
    }
}
