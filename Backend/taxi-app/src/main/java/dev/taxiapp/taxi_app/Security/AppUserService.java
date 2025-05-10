package dev.taxiapp.taxi_app.Security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.taxiapp.taxi_app.Services.AuthService;
import lombok.AllArgsConstructor;



@AllArgsConstructor
@Service
public class AppUserService implements UserDetailsService{
    private final AuthService authService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new AppUser(authService.getUserByUsername(username));
    }
    
}
