package dev.taxiapp.taxi_app.Configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Setter;


@Component
@Setter
@ConfigurationProperties(prefix = "app")
public class PropertiesProvider {
    private int verify_duration;
    private String email;
    private String jwt_key;


    public int verifyDuration() {
        return verify_duration;
    }

    public String email() {
        return email;
    }

    public String jwtKey() {
        return jwt_key;
    }

    
    
    

}
