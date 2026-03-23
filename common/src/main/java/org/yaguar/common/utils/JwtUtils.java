package org.yaguar.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtils {
    public String generateToken(String username, String role, Key secret, Long expire) {
        return Jwts.builder()
                .subject(username)
                .expiration(new Date(System.currentTimeMillis() + expire))
                .claim("role", role)
                .issuedAt(new Date())
                .signWith(secret)
                .compact();
    }

    public boolean validateToken(String token, Key secret) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) secret)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver, Key secret) {
        return claimsResolver.apply(Jwts.parser()
                .verifyWith((SecretKey) secret)
                .build()
                .parseSignedClaims(token)
                .getPayload());
    }

    public String userNameFromToken(String token, Key secret) {
        return getClaim(token, Claims::getSubject, secret);
    }

    public String getRole(String token, Key secret) {
        return getClaim(token, claims -> (String) claims.get("role"), secret);
    }
}
