package com.kal.club.view;

import com.kal.club.Entity.Role;
import com.kal.club.Entity.User;
import com.kal.club.Entity.UserRoles;
import com.kal.club.Service.RoleService;
import com.kal.club.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component //comment out when updating and not creating
public class SeedData implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {

        Role member = new Role("MEMBER");
        Role executive = new Role("EXECUTIVE");
        Role admin = new Role("ADMIN");

        roleService.save(member);
        roleService.save(admin);
        roleService.save(executive);

        User kal = new User("kalvin", "","zhao","kalvinzhao11","12345678aA");
        kal.getRoles().add(new UserRoles(kal, admin));
        userService.save(kal);
    }

}
