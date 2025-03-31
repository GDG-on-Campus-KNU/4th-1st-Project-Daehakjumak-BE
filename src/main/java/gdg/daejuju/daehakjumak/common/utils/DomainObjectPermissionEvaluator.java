package gdg.daejuju.daehakjumak.common.utils;

import gdg.daejuju.daehakjumak.auth.application.DomainAuthService;
import gdg.daejuju.daehakjumak.jumak.domain.Jumak;
import gdg.daejuju.daehakjumak.menu.domain.Menu;
import gdg.daejuju.daehakjumak.table.domain.Table;
import gdg.daejuju.daehakjumak.waiting.domain.Waiting;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@RequiredArgsConstructor
public class DomainObjectPermissionEvaluator implements PermissionEvaluator {

    private final DomainAuthService domainAuthService;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String userId = authentication.getName();

        if (targetDomainObject instanceof Jumak) {
            return domainAuthService.canAccessJumak(userId, ((Jumak) targetDomainObject).getId());
        } else if(targetDomainObject instanceof Waiting){
            return domainAuthService.canAccessWaiting(userId, ((Waiting) targetDomainObject).getId());
        } /*else if (targetDomainObject instanceof Order) {
            return domainAuthService.canAccessOrder(userId, ((Order) targetDomainObject).getId());
        } else if(targetDomainObject instanceof Table){
            return domainAuthService.canAccessTable(userId, ((Table) targetDomainObject).getId());
        } else if(targetDomainObject instanceof Menu){
            return domainAuthService.canAccessMenu(userId, ((Menu) targetDomainObject).getId());
        }*/
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        String userId = authentication.getName();
        Long id = (Long) targetId;

        switch (targetType) {
            case "Jumak": return domainAuthService.canAccessJumak(userId, id);
            case "Waiting": return domainAuthService.canAccessWaiting(userId, id);
            /*case "Order": return domainAuthService.canAccessOrder(userId, id);
            case "Table": return domainAuthService.canAccessTable(userId,id);
            case "Menu": return domainAuthService.canAccessMenu(userId, id);*/
            default: return false;
        }
    }
}
