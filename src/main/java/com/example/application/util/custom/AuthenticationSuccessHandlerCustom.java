package com.example.application.util.custom;

import com.example.application.constants.RoleConstant;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AuthenticationSuccessHandlerCustom implements AuthenticationSuccessHandler {
    protected final Log logger = LogFactory.getLog(this.getClass());
    private String defaultTargetUrl = "/login";
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private String targetUrlParameter = null;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//        String targetUrl = determineTargetUrl(request, response, authentication);
//        if (response.isCommitted()) {
//            this.logger.debug(LogMessage.format("Did not redirect to %s since response already committed.", targetUrl));
//            return;
//        }
//        this.redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        List<String> userRoles = authentication.getAuthorities().stream().map(grantedAuthority -> {
            return grantedAuthority.getAuthority();
        }).collect(Collectors.toList());
        if (isEditRole(userRoles)) {
            return "/admin";
        }
        return defaultTargetUrl;
    }

    boolean isEditRole(List<String> roles) {
        for (String role : roles) {
            if (Arrays.stream(RoleConstant.EDIT_ROLES).toList().contains(role.substring(role.indexOf('_') + 1))) {
                return true;
            }
        }
        return false;
    }

    boolean isUser(List<String> roles) {
        return roles.contains(RoleConstant.USER_ROLE);
    }
}
