package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/CreateNewRole")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);

    }
}
