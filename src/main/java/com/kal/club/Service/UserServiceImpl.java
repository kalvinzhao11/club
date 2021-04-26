package com.kal.club.Service;

import com.kal.club.DTO.UserDTO;
import com.kal.club.Entity.Role;
import com.kal.club.Entity.User;
import com.kal.club.Entity.UserRoles;
import com.kal.club.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User save(UserDTO user) {
        if (user.getUserId() != 0)
        {
            user = userRepo.findById(user.getUserId())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
        }
        user.setFname(user.getFname().toLowerCase());
        user.setMname(user.getMname().toLowerCase());
        user.setLname(user.getLname().toLowerCase());
        user.setEmail(user.getEmail()
                .toLowerCase());
        user.setPasswordNoEncrypt(user.getPassword());
        user.setEmail(user.getEmail()
                .toLowerCase());

        user.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());
            user.getRoles()
                    .add(new UserRoles(user,
                            addRole));
        }

        return userRepo.save(user);
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return null;
    }
}
