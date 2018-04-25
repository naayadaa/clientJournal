package naayadaa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by AnastasiiaDepenchuk on 24-Apr-18.
 */
@Service
public class AccessTokenService {

    @Autowired
    ClientDetailsService clientDetailsService;

    @Autowired
    AuthorizationServerTokenServices authorizationServerTokenServices;

    public String getCurrentUserAccessToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ClientDetails client = clientDetailsService.loadClientByClientId("clientJournalResource");
        OAuth2Request oAuth2Request = new OAuth2Request(new HashMap<String, String>(), client.getClientId(), client.getAuthorities(), true, client.getScope(), client.getResourceIds(), (String)null, (Set)null, (Map)null);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        return accessToken.getValue();
    }
}
