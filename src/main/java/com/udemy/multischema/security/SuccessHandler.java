package com.udemy.multischema.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SuccessHandler implements AuthenticationSuccessHandler {
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String username = httpServletRequest.getParameter("username");
        if(username!= null && username.equalsIgnoreCase("SUPER_ADMIN")){
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/hospital");
        }else {
            redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/doctor");
        }
    }
}
