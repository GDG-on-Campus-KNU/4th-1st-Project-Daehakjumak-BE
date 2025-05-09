package gdg.daejuju.daehakjumak.order.ui;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.order.application.OrderService;
import gdg.daejuju.daehakjumak.order.domain.dto.request.CreateOrderRequestDto;
import gdg.daejuju.daehakjumak.order.domain.dto.request.ModifyOrderRequestDto;
import gdg.daejuju.daehakjumak.order.domain.dto.request.ModifyOrderStatusRequestDto;
import gdg.daejuju.daehakjumak.order.domain.dto.response.GetOrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{tableId}")
    public Response<String> createOrder(@PathVariable Long tableId, @RequestBody CreateOrderRequestDto requestDto){
        return orderService.createOrder(tableId, requestDto);
    }

    @DeleteMapping("/{orderId}")
    public Response<String> deleteOrder(@PathVariable Long orderId){
        return orderService.deleteOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public Response<String> modifyOrder(@PathVariable Long orderId, @RequestBody ModifyOrderRequestDto requestDto){
        return orderService.modifyOrder(orderId, requestDto);
    }

    @GetMapping("/{orderId}")
    public Response<GetOrderResponseDto> getOrder(@PathVariable Long orderId){
        return orderService.getOrder(orderId);
    }

    @PutMapping("/status/{orderId}")
    public Response<String> modifyStatusOrder(@PathVariable Long orderId, @RequestBody ModifyOrderStatusRequestDto requestDto){
        return orderService.modifyStatusOrder(orderId, requestDto);
    }




}
