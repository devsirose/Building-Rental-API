package com.example.application.security.filter;

import com.example.application.constants.SecurityConstant;
import com.example.application.util.jwt.JWTokenAuthenticationUtil;
import com.example.application.util.jwt.impl.BasicJWTokenAuthenticationUtil;
import com.nimbusds.jwt.SignedJWT;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JWTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JWTokenAuthenticationUtil authUtil = new BasicJWTokenAuthenticationUtil();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(SecurityConstant.JWT_HEADER);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        token = token.substring(token.indexOf(" ") + 1);
        try {
            if (authUtil.isValidToken(token)) {
                SignedJWT signedJWT = SignedJWT.parse(token);
                String subject = signedJWT.getJWTClaimsSet().getSubject();
                UsernamePasswordAuthenticationToken authResult = new UsernamePasswordAuthenticationToken(subject, null,
                        AuthorityUtils.commaSeparatedStringToAuthorityList((String) signedJWT.getJWTClaimsSet().getClaim(SecurityConstant.JWT_AUTHORITIES)));
                authResult.setDetails(signedJWT.getJWTClaimsSet().getClaims());
                SecurityContext context = SecurityContextHolder.createEmptyContext();
                context.setAuthentication(authResult);
                SecurityContextHolder.setContext(context);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            filterChain.doFilter(request, response);
        }
    }
}
