package com.rightfindpro.become.controller;

import com.rightfindpro.become.domain.Role;
import com.rightfindpro.become.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController

public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/CreateNewRole")
    public Role createNewRole(@RequestBody Role role) {
        return roleService.createNewRole(role);

    }
    @DeleteMapping("/Role/{id}")
    public void deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
    }
}
