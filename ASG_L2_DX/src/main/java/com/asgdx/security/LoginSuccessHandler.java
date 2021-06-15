package com.asgdx.security;

import com.asgdx.service.UserService;
import com.asgdx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Autowired
    UserService userService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        User user = (User) authentication.getPrincipal();

        String redirectURL = request.getContextPath();

        if (userService.hasRole(user,"ROLE_MANAGER" )){
            redirectURL = "/manager/home";
        } else if (userService.hasRole(user,"ROLE_EMPLOYEE")) {
            redirectURL = "/employee/home";
        }
        response.sendRedirect(request.getContextPath()+ redirectURL);
    }
}