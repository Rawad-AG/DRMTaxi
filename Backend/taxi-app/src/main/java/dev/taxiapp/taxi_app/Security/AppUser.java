package dev.taxiapp.taxi_app.Security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import dev.taxiapp.taxi_app.Entities.UserEntity;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AppUser implements UserDetails{
    private final UserEntity user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.user.getAuth().getRoles().stream().map(role-> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getAuth().getPassword();
    }

    @Override
    public String getUsername() {
        if(this.user.getEmail() != null && !this.user.getEmail().isEmpty()) return this.user.getEmail();
        return this.user.getPhoneNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !this.user.getDeleted();
    }

    @Override
    public boolean isAccountNonLocked() {
        return !this.user.getAuth().getLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !this.user.getAuth().getCredintialsExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.user.getAuth().getEnabled();
    }
}
