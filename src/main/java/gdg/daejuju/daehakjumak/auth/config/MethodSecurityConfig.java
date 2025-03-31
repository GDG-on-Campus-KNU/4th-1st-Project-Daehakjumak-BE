package gdg.daejuju.daehakjumak.auth.config;

import gdg.daejuju.daehakjumak.auth.application.DomainAuthService;
import gdg.daejuju.daehakjumak.common.utils.DomainObjectPermissionEvaluator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;

@Configuration
public class MethodSecurityConfig {

    @Bean
    public PermissionEvaluator permissionEvaluator(DomainAuthService domainAuthService) {
        return new DomainObjectPermissionEvaluator(domainAuthService);
    }

    @Bean
    public MethodSecurityExpressionHandler methodSecurityExpressionHandler(PermissionEvaluator permissionEvaluator) {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        return expressionHandler;
    }
}
