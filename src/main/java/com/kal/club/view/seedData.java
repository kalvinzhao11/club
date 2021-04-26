package com.kal.club.view;

import com.kal.club.Entity.Role;
import com.kal.club.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class seedData implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        @Autowired
        RoleService roleService;

        Role member = new Role("MEMBER");
        Role executive = new Role("EXECUTIVE");
        Role admin = new Role("ADMIN");
    }
}
