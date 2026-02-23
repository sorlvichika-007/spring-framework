package com.testing.science.security.jwt;

import com.testing.science.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    @Value("${spring.jwt.secret}")
    private String secret_key;
    @Value("${spring.jwt.expirationAccess}")
    private Long accessExpiration;
    @Value("${spring.jwt.expirationRefresh}")
    private Long refreshExpiration;

    public Jwt generateAccessToken(User user){
        return generateToken(user,accessExpiration);
    }

    public Jwt generateRefreshToken(User user){
        return generateToken(user,refreshExpiration);
    }

    public Jwt generateToken(User user, long tokenExpiration){
        var claims = Jwts.claims()
                .subject(user.getId().toString())
                .add("email", user.getEmail())
                .add("name", user.getUsername())
                .add("role", user.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();
        return new Jwt(claims,getSecretKey());
    }

    public Jwt parseToken(String token){
        try {
            var claims = getClaims(token);
            return new Jwt(claims,getSecretKey());
        }catch (JwtException ex){
            return null;
        }
    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(secret_key.getBytes());
    }

}