package com.rightfindpro.become.user;


import com.rightfindpro.become.domain.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public Role createNewRole(Role role) {
        return roleRepository.save(role);
    }
    public void deleteRole(Integer id) {
        roleRepository.deleteById(id);
    }

}
