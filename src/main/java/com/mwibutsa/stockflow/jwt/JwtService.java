package com.mwibutsa.stockflow.jwt;

import com.mwibutsa.stockflow.auth.user.User;
import com.mwibutsa.stockflow.config.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {
    private final JwtConfig jwtConfig;


    private Jwt generateToken(User user, Long tokenExpiration) {
        var claims = Jwts.claims().subject(user.getId().toString())
                .add("firstName", user.getFirstName())
                .add("lastName", user.getLastName())
                .add("email", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .build();
        return new Jwt(claims, jwtConfig.getSecretKey());
    }

    private Claims getClaims(String token) {
        return Jwts.parser().verifyWith(jwtConfig.getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Jwt parseToken(String token) {
        try {
            var claims = getClaims(token);
            return new Jwt(claims, jwtConfig.getSecretKey());
        } catch (JwtException e) {
            return null;
        }
    }

    public Jwt generateRefreshToken(User user) {
        return generateToken(user, (long) jwtConfig.getRefreshTokenExpiration());
    }

    public Jwt generateAccessToken(User user) {
        return generateToken(user, (long) jwtConfig.getAccessTokenExpiration());
    }
}
