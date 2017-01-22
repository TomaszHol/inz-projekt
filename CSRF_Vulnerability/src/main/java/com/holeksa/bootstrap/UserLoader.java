package com.holeksa.bootstrap;

import com.holeksa.model.Role;
import com.holeksa.model.User;
import com.holeksa.repository.RoleRepository;
import com.holeksa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by bourbonkid on 18.01.17.
 *
 * This is class that is load each time when you start the appliaction. Class add user:
 *      @username: guest
 *      @password: guest
 *      @role: USER
 */

@Component
public class UserLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        User guest = new User();
        guest.setUsername("guest");
        guest.setPassword("$2a$10$9N21FKNQ6TUz8.FEgFAK9.6AbfcVCEsoVcuuNndWsKEluguf33qgG");

        Role role = new Role();
        role.setName("USER");
        roleRepository.save(role);

        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);

        guest.setRoles(roleSet);

        userRepository.save(guest);


    }
}
