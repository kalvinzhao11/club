package com.kal.club.Service;

import com.kal.club.DTO.UserDTO;
import com.kal.club.Entity.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

//    List<User> findByNameContaining(String username);

    User findUserById(long id);

//    User findByName(String name);

//    User findByUserEmail(String email);

    void delete(long id);

    User save(User user);

    User update(User user, long id);
}
