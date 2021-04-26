package com.kal.club.Service;

import com.kal.club.DTO.UserDTO;
import com.kal.club.Entity.User;

import java.util.List;

public interface UserService {
    // save users
    User save(UserDTO user);

    // delete users
    //void delete(UserDTO userDTO);

    // update users
    //void updateUsers(UserDTO UserDTO);
    //

    // get all users
    List<UserDTO> findAllUsers();
}
