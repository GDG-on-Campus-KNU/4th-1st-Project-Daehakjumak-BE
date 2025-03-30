package gdg.daejuju.daehakjumak.auth.application;

import gdg.daejuju.daehakjumak.jumak.application.interfaces.JumakRepository;
import gdg.daejuju.daehakjumak.menu.application.interfaces.MenuRepository;
import gdg.daejuju.daehakjumak.purchaseHistory.application.PurchaseHistoryService;
import gdg.daejuju.daehakjumak.table.application.interfaces.TableRepository;
import gdg.daejuju.daehakjumak.user.application.interfaces.UserRepository;
import gdg.daejuju.daehakjumak.waiting.application.interfaces.WaitingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DomainAuthService {

    private final UserRepository userRepository;
    private final JumakRepository jumakRepository;
    private final WaitingRepository waitingRepository;
    /*private final MenuRepository menuRepository;
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;*/

    public boolean canAccessJumak(String userId, Long jumakId) {
        return jumakRepository.isAcessibleByUser(jumakId, userId);
    }

    public boolean canAccessWaiting(String userId, Long waitingId) {
        return waitingRepository.isAccesibleByUser(waitingId, userId);
    }

   /* public boolean canAccessMenu(String userId, Long menuId) {
        return menuRepository.isAccessibleByUser(menuId, userId);
    }

    public boolean canAccessTable(String userId, Long tableId) {
        return tableRepository.isAccessibleByUser(tableId, userId);
    }

    public boolean canAccessOrder(String userId, Long orderId) {
        return orderRepository.isAccessibleByUser(orderId, userId);
    }*/
}