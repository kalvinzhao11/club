package com.kal.club.Service;

import com.kal.club.Entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findRoleById(long id);

    Role save(Role role);

    Role findByName(String roleName);

    public void deleteAll();

    Role update(long id, Role role);

    void delete(long id);
}
