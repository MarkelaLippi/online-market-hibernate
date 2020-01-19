package com.gmail.roadtojob2019.servicemodule.services.dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AppUserPrincipal implements UserDetails {
    private UserDTO userDTO;
    private Set<GrantedAuthority> roles=new HashSet<>();

    public AppUserPrincipal(UserDTO userDTO) {
        this.userDTO = userDTO;
        roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(userDTO.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getEmail();
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
