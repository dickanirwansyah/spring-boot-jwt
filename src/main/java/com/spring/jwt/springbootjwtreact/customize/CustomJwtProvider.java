package com.spring.jwt.springbootjwtreact.customize;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CustomJwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(CustomJwtProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;


    public String generateToken(Authentication authentication){

        CustomUserPrincipal userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(userPrincipal.getIdusers())
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public Long getUsersIdFromJWT(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }


    public boolean validateToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }catch (SignatureException ex){
            logger.error("Signature JWT Tidak valid");
        }catch (MalformedJwtException ex){
            logger.error("Bentuk dari dari JWT Tidak valid");
        }catch (ExpiredJwtException ex){
            logger.error("masa waktu token kadaluarsa");
        }catch (UnsupportedJwtException ex){
            logger.error("Jwt token tidak support");
        }catch (IllegalArgumentException ex){
            logger.error("Jwt claims string kosong");
        }
        return false;
    }
}
