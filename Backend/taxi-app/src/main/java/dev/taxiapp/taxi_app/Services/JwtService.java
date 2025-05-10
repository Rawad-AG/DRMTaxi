package dev.taxiapp.taxi_app.Services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@AllArgsConstructor
@Service
public class JwtService {

    private final SecretKey SECRET_KEY;

    public String sign(Claims claims, String subject, int expires) {
        return Jwts.builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expires))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String sign(String subject, int expires) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expires))
                .signWith(SECRET_KEY)
                .compact();
    }

    public Claims verify(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(SECRET_KEY)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
        } catch (ExpiredJwtException e) {
            throw new JwtException("expired token");
        } catch (JwtException e) {
            throw new JwtException("Invalid token");
        }
    }


}

