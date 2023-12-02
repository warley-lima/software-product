package com.Security.Business;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer{
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken,OAuth2Authentication authentication) {
        Map<String, Object> additionalInfo = new HashMap<>();
        String scope = "";
        scope += authentication.getAuthorities().iterator().next().getAuthority();
        authentication.getOAuth2Request().getRequestParameters().forEach((k,v)->System.out.println(k + ": " + v));
        additionalInfo.put("scope", scope);
        additionalInfo.put("user", authentication.getUserAuthentication().getName());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
