package org.example.elegant.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {
    @Value("${elegant.security.token.expiration}")
    private long expiration;
    @Value("${elegant.security.token.secret}")
    private String secret;

    public String generateToken(String email) {
        Date now = new Date();
        Date expiration = Date.from(now.toInstant().plusSeconds(this.expiration));

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(signingKey())
                .compact();
    }

    public Key signingKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(secret));
    }

    public Claims claims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
