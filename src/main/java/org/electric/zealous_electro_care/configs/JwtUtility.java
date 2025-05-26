package org.electric.zealous_electro_care.configs;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtility {
    private final String secret = "2pQrcPHTt+ItCBZi9I/VxHzuAUZK9bCltDIWPakqL6Y=";

    public String generateToken(String username){
        Map<String,Object> myClaims = new HashMap<>();
        return Jwts.builder().claims().add(myClaims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+60*60*30)).and()
        .signWith(getKey()).compact();
    }
    private Key getKey(){
        byte[] arr = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(arr);
    }
    public Jws<Claims> validateToken(String token){
        return Jwts.parser().setSigningKey(getKey()).build().parseClaimsJws(token);
    }
}