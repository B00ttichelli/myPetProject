package com.vovnenko.mypetproject.security.Jwt;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final JwtProvider jwtProvider;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtParser build = Jwts.parserBuilder()
                .setSigningKey(jwtProvider.getSignKey()).build();
        String subject = build.parseClaimsJws(authentication.getName())   /// Authentication  TODO DEBUG
                .getBody().getSubject();
        List<String> au = (List<String>) build.parseClaimsJws(authentication.getName()).getBody().get("authorities");
        return new UsernamePasswordAuthenticationToken(subject,"",au.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
