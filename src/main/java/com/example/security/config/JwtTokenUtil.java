package com.example.security.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;


@Component
public class JwtTokenUtil implements Serializable {
    @Serial
    private static final long serialVersionUID = -2550185165626007488L;

    private final String SECRET;
    public final long JWT_TOKEN_VALIDITY;

    public JwtTokenUtil(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.live-timer}") long liveTimer
    ) {
        this.SECRET = secret;
        this.JWT_TOKEN_VALIDITY = liveTimer;
    }


    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, @NotNull UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (userDetails.getUsername().equals(username)) && !isTokenExpired(token);
    }

    public String generateToken(@NotNull UserDetails userDetails) {
        HashMap<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }


    private <T> T getClaimFromToken(String token, @NotNull Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private String doGenerateToken(HashMap<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }
}
