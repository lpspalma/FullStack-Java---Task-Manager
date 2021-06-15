package com.asgdx;

import com.asgdx.model.Role;
import com.asgdx.model.User;
import com.asgdx.repository.RoleRepository;
import com.asgdx.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class LoadDataBase {

    private static final Logger log = LoggerFactory.getLogger(LoadDataBase.class);
    private String preloading = "Preloading ";

    @Bean
    CommandLineRunner initRoleDatabase(RoleRepository repository) {
        return args -> {
            log.info(preloading + repository.save(new Role("ROLE_MANAGER")));
            log.info(preloading + repository.save(new Role("ROLE_EMPLOYEE")));
        };
    }

    @Bean
    CommandLineRunner initUserAdminDatabase(UserRepository repository, RoleRepository roleRepository) {
        return args -> {
            Set<Role> userRoles = new HashSet<>();
            userRoles.add(roleRepository.findByName("ROLE_MANAGER"));
            //admin123
            log.info(preloading + repository.save(new User("admin","$2a$10$GKsdi/NHVvZD3ioN5E3sQuOssuyQmOcUtGfPNdj882cF8QldTph2K",userRoles)));
        };
    }
}
