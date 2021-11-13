package com.vovnenko.mypetproject.security.filters;

import com.vovnenko.mypetproject.security.Jwt.JwtProvider;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


@AllArgsConstructor
@Slf4j
@Component
public class JWTFilter extends OncePerRequestFilter  {

    private final JwtProvider jwtProvider;
    private final AuthenticationManager authenticationManager;




    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwtFromRequest = getJwtFromRequest(request);

        if(jwtFromRequest != null){
            Authentication authentication = null;
            try {
                authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(jwtFromRequest,null));
            } catch (AuthenticationException e) {
                log.info("Something went wrong : token "+ jwtFromRequest + " Exception Mesage: " + e.getMessage());
            }
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
            filterChain.doFilter(request,response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        return Optional.ofNullable(request.getHeader("Authorization"))
                .filter(authHeader->authHeader.startsWith("Bearer "))
                .map(token->token.substring(7))
                .orElse(null);
    }


}
