package com.vovnenko.mypetproject.security.Jwt;


import com.vovnenko.mypetproject.Enum.ROLE;
import com.vovnenko.mypetproject.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

@Component
@AllArgsConstructor
public class JwtProvider {

    //to Do add constructor and put it in app.prop
    private final Integer AccessTokenTimeInMin = 120;
    private final Integer refreshTokenTimeInMin = 600;
    private final String TokenKey = "secretKey;))()*)IOJSD(*YSHKNSO(A*SYHNMSDOAKFN)(IJJKNDSDISAHJBNF<NBFNNMLJIUGSBNKOJIUYGHBJNKIUGYFVHBJKNHIY*FYUTT*YIUIUT^RFYHV";

    private final Key signKey;

    @Autowired
    public JwtProvider(){
        byte[] bytes = DatatypeConverter.parseBase64Binary(TokenKey);
        this.signKey = new SecretKeySpec(bytes,SignatureAlgorithm.HS512.getJcaName());

    }

    public String createToken(String username, ROLE role){
        Date date = new Date();
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("authorities", Collections.singleton(role.name()));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE,AccessTokenTimeInMin);


        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(date)
                .setExpiration(calendar.getTime())
                .signWith(signKey)
                .compact();

    }

    public String createRefreshToken(User user){
        Claims claims =  Jwts.claims().setSubject(user.getUsername());
        claims.put("authorities",Collections.singleton(ROLE.USER));
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.MINUTE,refreshTokenTimeInMin);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(calendar.getTime())
                .signWith(signKey)
                .compact();
    }


}

