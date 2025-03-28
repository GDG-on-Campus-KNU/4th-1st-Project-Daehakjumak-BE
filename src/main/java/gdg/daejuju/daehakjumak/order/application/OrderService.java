package gdg.daejuju.daehakjumak.order.application;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.order.application.interfaces.OrderRepository;
import gdg.daejuju.daehakjumak.order.domain.dto.request.CreateOrderRequestDto;
import gdg.daejuju.daehakjumak.table.domain.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;


    @Transactional
    public Response<String> createOrder(Long tableId, CreateOrderRequestDto requestDto){


        return Response.ok("주문이 정상 입력됐습니다.");
    }
}
