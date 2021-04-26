package com.kal.club.Service;

import com.kal.club.Entity.Role;

import java.util.List;

public interface RoleService {
    Role save(Role role);
    Role update(long id, Role role);
    List<Role> findAll();
}
