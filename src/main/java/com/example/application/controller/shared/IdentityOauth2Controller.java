package com.example.application.controller.shared;

import com.example.application.service.IdentityOauth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login/oauth2/code")
public class IdentityOauth2Controller {
    private final IdentityOauth2Service identityOauth2Service;
    @Autowired
    public IdentityOauth2Controller(IdentityOauth2Service identityOauth2Service) {
        this.identityOauth2Service = identityOauth2Service;
    }

    @GetMapping(value = "/github")
    public ResponseEntity<?> processGithub(@RegisteredOAuth2AuthorizedClient("github") OAuth2AuthorizedClient authorizedClient) {
        return ResponseEntity.ok(identityOauth2Service.doAuthenticateInternal(authorizedClient));
    }
}
