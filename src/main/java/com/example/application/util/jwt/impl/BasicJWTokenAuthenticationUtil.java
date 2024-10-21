package com.example.application.util.jwt.impl;

import com.example.application.constants.SecurityConstant;
import com.example.application.util.jwt.JWTokenAuthenticationUtil;
import com.example.application.util.map.GrantedUserUtil;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Date;

@Component
public class BasicJWTokenAuthenticationUtil implements JWTokenAuthenticationUtil {
    @Override
    public String generateToken(String subject, Collection<? extends GrantedAuthority> collections, Date expirationTime) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(subject)
                .issueTime(new Date())
                .expirationTime(expirationTime)
                .claim(SecurityConstant.JWT_AUTHORITIES, GrantedUserUtil.toString(collections))
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);
        jwsObject.sign(new MACSigner(SecurityConstant.JWT_KEY.getBytes()));
        return jwsObject.serialize();
    }

    @Override
    public boolean isValidToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(SecurityConstant.JWT_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.verify(verifier) && signedJWT.getJWTClaimsSet().getExpirationTime().after(new Date());
    }

    @Override
    public String parseSubject(String token) throws ParseException, JOSEException {
        if(!isValidToken(token)){
            throw new JOSEException("Invalid JWT token");
        }
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getSubject();
    }

}
