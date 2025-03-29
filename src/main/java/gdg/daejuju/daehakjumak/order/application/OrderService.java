package gdg.daejuju.daehakjumak.order.application;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.order.application.interfaces.OrderRepository;
import gdg.daejuju.daehakjumak.order.domain.Order;
import gdg.daejuju.daehakjumak.order.domain.OrderStatus;
import gdg.daejuju.daehakjumak.order.domain.dto.request.CreateOrderRequestDto;
import gdg.daejuju.daehakjumak.order.repository.entity.OrderEntity;
import gdg.daejuju.daehakjumak.table.application.interfaces.TableRepository;
import gdg.daejuju.daehakjumak.table.domain.Table;
import gdg.daejuju.daehakjumak.table.repository.entity.TableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;

    @Transactional
    public Response<String> createOrder(Long tableId, CreateOrderRequestDto requestDto){

        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("테이블이 존재하지 않습니다."));

        Order order = new Order(null, requestDto.getMenu(), requestDto.getQuantity(), requestDto.getPrice(), OrderStatus.ORDERED, table.toTable());
        OrderEntity orderEntity = new OrderEntity(order);

        orderRepository.save(orderEntity);

        return Response.ok("주문이 정상 입력됐습니다.");
    }

    @Transactional
    public Response<String> deleteOrder(Long orderId){

        orderRepository.deleteById(orderId);

        return Response.ok("정상적으로 삭제됐습니다.");
    }
}
