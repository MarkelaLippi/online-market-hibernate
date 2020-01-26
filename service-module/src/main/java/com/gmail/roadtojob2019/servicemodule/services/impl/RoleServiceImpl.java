package com.gmail.roadtojob2019.servicemodule.services.impl;

import com.gmail.roadtojob2019.repositorymodule.models.Role;
import com.gmail.roadtojob2019.servicemodule.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Override
    @Transactional
    public List<Role> findAllRoles() {
        return Arrays.asList(Role.values());
    }
}
