package dev.taxiapp.taxi_app.Configs;

import java.util.Arrays;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Configuration
public class EnvironmentChecker {
    private final Environment env;


    public boolean isDevelopment(){
        return Arrays.stream(this.env.getActiveProfiles()).anyMatch(p -> p.equalsIgnoreCase("dev"));
    }

    public boolean isProduction(){
        return Arrays.stream(this.env.getActiveProfiles()).anyMatch(p -> p.equalsIgnoreCase("prod"));
    }
}
