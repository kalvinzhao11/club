package com.kal.club.Service;

import com.kal.club.Entity.Role;
import com.kal.club.Exception.ResourceNotFoundException;
import com.kal.club.Repo.RoleRepo;
import com.kal.club.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepo roleRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    private UserAuditing userAuditing;


    @Override
    public List<Role> findAll() {
        List<Role> list = new ArrayList<>();

        roleRepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Role findRoleById(long id) {
            return roleRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Chapter id " + id + " not found!"));
    }

    @Transactional
    @Override
    public Role save(Role role) {
        if (role.getUsers()
                .size() > 0) {
            throw new ResourceNotFoundException("User Roles are not updated through Role.");
        }

        return roleRepo.save(role);
    }

    @Override
    public Role findByName(String roleName) {
        Role rr = roleRepo.findByNameIgnoreCase(roleName);

        if (rr != null)
        {
            return rr;
        } else
        {
            throw new ResourceNotFoundException("Illegal Role Name");
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        roleRepo.deleteAll();
    }

    @Transactional
    @Override
    public Role update(long id, Role role) {
        if (role.getName() == null) {
            throw new ResourceNotFoundException("No role name found to update!");
        }

        if (role.getUsers()
                .size() > 0) {
            throw new ResourceNotFoundException("User Roles are not updated through Role. See endpoint POST: users/user/{userid}/role/{roleid}");
        }

        Role newRole = findRoleById(id); // see if id exists

        roleRepo.updateRoleName(userAuditing.getCurrentAuditor()
                        .get(),
                id,
                role.getName());
        return findRoleById(id);
    }

    @Override
    public void delete(long id) {
        roleRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Role id " + id + " not found!"));
        roleRepo.deleteById(id);
    }
}
