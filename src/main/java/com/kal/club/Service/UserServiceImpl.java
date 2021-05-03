package com.kal.club.Service;

import com.kal.club.Entity.Role;
import com.kal.club.Entity.User;
import com.kal.club.Entity.UserRoles;
import com.kal.club.Exception.ResourceNotFoundException;
import com.kal.club.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userService")
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Autowired
    RoleService roleService;

    @Autowired
    HelperFunctions helperFunctions;

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userRepo.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findUserById(long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id) {
        userRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userRepo.deleteById(id);
    }

    @Transactional
    @Override
    public User update(User user, long id) {

        User currentUser = findUserById(id);

        if(helperFunctions.isAuthorizedToMakeChange(currentUser.getUsername())) {

            if (user.getUsername() != null) {
                currentUser.setUsername(user.getUsername()
                        .toLowerCase());
            }

            if (user.getPassword() != null) {
                currentUser.setPasswordNoEncrypt(user.getPassword());
            }

//            if (user.getEmail() != null) {
//                currentUser.setEmail(user.getEmail()
//                        .toLowerCase());
//            }

            if (user.getRoles()
                    .size() > 0) {
                currentUser.getRoles()
                        .clear();
                for (UserRoles ur : user.getRoles()) {

                    Long roleId = ur.getRole().getRoleid();
                    Role addRole;

                    //False Update to Admin
                    if(roleService.findRoleById(roleId).equals(roleService.findByName("ADMIN")) && !helperFunctions.isAdmin()) {
                        addRole = roleService.findByName("BREACHER");
                    }
                    //Update to Admin
                    else {addRole = roleService.findRoleById(roleId);}

                    currentUser.getRoles()
                            .add(new UserRoles(currentUser,
                                    addRole));
                }
            }

            return userRepo.save(currentUser);
        }
        else {
            throw new ResourceNotFoundException("User not authorized to make changes");
        }
    }

    @Override
    public User save(User user) {

        User newUser = new User();

        if (user.getUserid() != 0) {
            newUser = userRepo.findById(user.getUserid())
                    .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
        }
        newUser.setFname(user.getFname().toLowerCase());
        newUser.setMname(user.getMname().toLowerCase());
        newUser.setLname(user.getLname().toLowerCase());
        newUser.setUsername(user.getUsername().toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());

        newUser.getRoles()
                .clear();
        for (UserRoles ur : user.getRoles()) {
            Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());
            newUser.getRoles()
                    .add(new UserRoles(newUser,
                            addRole));
        }

        return userRepo.save(newUser);

    }

}
