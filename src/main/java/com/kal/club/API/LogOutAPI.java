package com.kal.club.API;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogOutAPI {
    @Autowired
    TokenStore tokenStore;

    @RequestMapping(value = {"/oauth/revoke-token", "/logout"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logoutSelf(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader != null) {
            String tokenValue = authHeader.replace("Bearer", "").trim();
            OAuth2AccessToken auth2AccessToken = tokenStore.readAccessToken(tokenValue);
            tokenStore.removeAccessToken(auth2AccessToken);
        }
    }
}
