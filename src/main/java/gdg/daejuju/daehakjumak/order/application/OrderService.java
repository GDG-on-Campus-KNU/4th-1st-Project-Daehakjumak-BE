package gdg.daejuju.daehakjumak.order.application;


import gdg.daejuju.daehakjumak.common.ui.Response;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.jumak.repository.JumakRepositoryImpl;
import gdg.daejuju.daehakjumak.menu.application.interfaces.MenuRepository;
import gdg.daejuju.daehakjumak.menu.repository.entity.MenuEntity;
import gdg.daejuju.daehakjumak.order.application.interfaces.OrderRepository;
import gdg.daejuju.daehakjumak.order.domain.Order;
import gdg.daejuju.daehakjumak.order.domain.OrderStatus;
import gdg.daejuju.daehakjumak.order.domain.dto.request.CreateOrderRequestDto;
import gdg.daejuju.daehakjumak.order.domain.dto.request.ModifyOrderRequestDto;
import gdg.daejuju.daehakjumak.order.domain.dto.request.ModifyOrderStatusRequestDto;
import gdg.daejuju.daehakjumak.order.domain.dto.response.GetOrderResponseDto;
import gdg.daejuju.daehakjumak.order.repository.entity.OrderEntity;
import gdg.daejuju.daehakjumak.purchaseHistory.application.PurchaseHistoryService;
import gdg.daejuju.daehakjumak.purchaseHistory.application.dto.CreatePurchaseHistoryReqDto;
import gdg.daejuju.daehakjumak.table.application.interfaces.TableRepository;
import gdg.daejuju.daehakjumak.table.repository.entity.TableEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final PurchaseHistoryService purchaseHistoryService;
    private final MenuRepository menuRepository;

    // 테이블에 주문 추가
    @Transactional
    public Response<String> createOrder(Long tableId, CreateOrderRequestDto requestDto){

        // 주문한 테이블 탐색
        TableEntity table = tableRepository.findById(tableId)
                .orElseThrow(() -> new IllegalArgumentException("테이블이 존재하지 않습니다."));


        // 메뉴 정보 탐색
        MenuEntity menu = menuRepository
                .findByJumakEntity_idAndName(
                        requestDto.getJumakId(),
                        requestDto.getMenu()
                )
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 정보입니다."));

        // 주문 정보 객체 생성
        Order order = new Order(
                null,
                requestDto.getMenu(),
                requestDto.getQuantity(),
                menu.getPrice() * requestDto.getQuantity(),
                OrderStatus.ORDERED, table.toTable());

        OrderEntity orderEntity = new OrderEntity(order);
        orderRepository.save(orderEntity);

        // 로그성 데이터 기록
        String id = purchaseHistoryService
                .savePurchaseHistory(
                        new CreatePurchaseHistoryReqDto(
                                requestDto.getMenu(),
                                requestDto.getQuantity(),
                                requestDto.getPrice(),
                                requestDto.getJumakId()
                        ));

        return Response.ok("주문이 정상 입력됐습니다.");
    }

    // 테이블 주문 삭제
    @Transactional
    public Response<String> deleteOrder(Long orderId){

        orderRepository.deleteById(orderId);

        return Response.ok("정상적으로 삭제됐습니다.");
    }

    // 주문 내용 변경
    @Transactional
    public Response<String> modifyOrder(Long orderId, ModifyOrderRequestDto requestDto){
        OrderEntity order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("해당 주문이 존재하지 않습니다."));
        order.setMenu(requestDto.getMenu());
        order.setQuantity(requestDto.getQuantity());
        order.setStatus(requestDto.getStatus());

        return Response.ok("정상적으로 수정됐습니다.");
    }

    // 주문 정보 불러오기
    @Transactional
    public Response<GetOrderResponseDto> getOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 주문 정보입니다."))
                .toOrder();

        GetOrderResponseDto dto = new GetOrderResponseDto(
                order.getMenu(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getTable().getId()
        );

        return Response.ok(dto);
    }

    // 주문 처리 상태 변경하기
    @Transactional
    public Response<String> modifyStatusOrder(Long orderId, ModifyOrderStatusRequestDto requestDto){

        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 주문 정보입니다."));

        order.setStatus(requestDto.getStatus().toString());
        return Response.ok(requestDto + " 변경 완료");
    }

}
