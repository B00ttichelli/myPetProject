package com.vovnenko.mypetproject.security;


import com.vovnenko.mypetproject.exceptions.CustomException;
import com.vovnenko.mypetproject.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProvider {
    @Value("${jwt.secret}")
    private String secret;

    private KeyStore keyStore;


    @PostConstruct
    public void init(){
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream inputStream = getClass().getResourceAsStream("/home/platon/IdeaProjects/myPetProject/src/main/resources/mykeys.keystore");
            keyStore.load(inputStream,"secret".toCharArray());
        } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException e) {
            throw new CustomException("Exception in th hole: Jwt Provider Keystore Init is failed ");
        }

    }

    public String generateToken(Authentication authentication)  {
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();

    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("secret","secret".toCharArray());
        } catch (UnrecoverableKeyException | KeyStoreException | NoSuchAlgorithmException e) {
            throw new CustomException("Exception in the hole: Retrieving public Key failed");
        }
    }
}
