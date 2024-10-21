package com.example.application.util.jwt;

import com.nimbusds.jose.JOSEException;
import org.springframework.security.core.GrantedAuthority;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

public interface JWTokenAuthenticationUtil {
    String generateToken(String subject, Collection<? extends GrantedAuthority> collections, Date expirationTime) throws JOSEException;

    boolean isValidToken(String token) throws JOSEException, ParseException;

    String parseSubject(String token) throws ParseException, JOSEException;
}