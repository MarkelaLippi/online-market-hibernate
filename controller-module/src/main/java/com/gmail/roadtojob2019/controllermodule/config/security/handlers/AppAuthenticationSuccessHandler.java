package com.gmail.roadtojob2019.controllermodule.config.security.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@Component
public class AppAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    //private static final Logger logger = LoggerFactory.getLogger(AppAuthenticationSuccessHandler.class);
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException {
        handle(httpServletRequest, httpServletResponse, authentication);
        clearAuthenticationAttributes(httpServletRequest);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);
        if (response.isCommitted()) {
            //logger.debug("Response has already been committed. Unable to redirect to {} ", targetUrl);
            return;
        }
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equalsIgnoreCase("ADMINISTRATOR")) {
                // logger.info("{} role detected, return  ", grantedAuthority.toString());
                return "/1";
            } else if (grantedAuthority.getAuthority().equalsIgnoreCase("SALE_USER")) {
                return "/2";
            } else if (grantedAuthority.getAuthority().equalsIgnoreCase("CUSTOMER_USER")) {
                return "/3";
            } else if (grantedAuthority.getAuthority().equalsIgnoreCase("SECURE_API_USER")) {
                return "/4";
            }
        }
        //       logger.debug("Not found matched roles, check success Handler");
        throw new IllegalStateException();
    }


    private void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }
}
