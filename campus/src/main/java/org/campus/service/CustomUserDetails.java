package org.campus.service;

import org.campus.pojo.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(User user) {
        this.username = user.getTelephone();
        this.password = String.valueOf(user.getId());
        this.authorities = mapRolesToAuthorities(user.getRoleType());
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Integer roleType) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        switch (roleType) {
            case 1:
                authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
                break;
            case 2:
                authorities.add(new SimpleGrantedAuthority("ROLE_PARENT"));
                break;
            case 3:
                authorities.add(new SimpleGrantedAuthority("ROLE_TUTOR"));
                break;
            case 4:
                authorities.add(new SimpleGrantedAuthority("ROLE_LEADER"));
                break;
            default:
                throw new IllegalArgumentException("Invalid role type");
        }
        return authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
