package com.asgdx.service;

import com.asgdx.model.Role;
import com.asgdx.model.User;
import com.asgdx.repository.RoleRepository;
import com.asgdx.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(roleRepository.findByName("ROLE_EMPLOYEE"));
        user.setRoles(userRoles);
        userRepository.save(user);
    }

    public User findById(Long id){
        return userRepository.getById(id);
    }

    public boolean hasRole(User user, String roleName) {
        for(Role role:user.getRoles()){
            if(role.getName().equals(roleName)) return true;
        }
        return false;
    }
}
