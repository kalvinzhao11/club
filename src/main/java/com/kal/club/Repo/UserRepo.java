package com.kal.club.Repo;

import com.kal.club.Entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findByUsernameContainingIgnoreCase(String username);

    User findByUsername(String username);
}
