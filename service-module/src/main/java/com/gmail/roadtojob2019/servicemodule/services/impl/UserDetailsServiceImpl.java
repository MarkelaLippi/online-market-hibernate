package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.servicemodule.services.UserService;
import com.gmail.roadtojob2019.servicemodule.services.dtos.AppUserPrincipal;
import com.gmail.roadtojob2019.servicemodule.services.dtos.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userService.findUserByEmail(email);
        if (userDTO == null) {
            throw new UsernameNotFoundException("User with such email was not found in database");
        }
        return new AppUserPrincipal(userDTO);
    }
}
