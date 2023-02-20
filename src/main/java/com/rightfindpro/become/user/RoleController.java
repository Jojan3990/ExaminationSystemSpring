package com.rightfindpro.become.user;

//import com.rightfindpro.become.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping("/CreateNewRole")
    public Role createNewRole(@RequestBody Role role) {
        System.out.println("This is working");
        return roleService.createNewRole(role);

    }
    @DeleteMapping("/Role/{id}")
    public void deleteRole(@PathVariable("id") int id) {
        roleService.deleteRole(id);
    }
}
