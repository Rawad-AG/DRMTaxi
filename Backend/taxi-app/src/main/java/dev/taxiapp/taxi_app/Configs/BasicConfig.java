package dev.taxiapp.taxi_app.Configs;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Configuration
public class BasicConfig {
    private final PropertiesProvider provider;

    @Bean
    public SecretKey secretKey(){
        System.out.println("provider.jwtKey() = " + provider.jwtKey());
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(this.provider.jwtKey()));
    }
}
