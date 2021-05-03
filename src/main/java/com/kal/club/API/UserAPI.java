package com.kal.club.API;

import com.kal.club.Entity.User;
import com.kal.club.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserAPI {

    @Autowired
    private UserService userService;

    //Get list of all users and their associated Role(s)
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping(value = "/users", produces = "application/json")
    public ResponseEntity<?> listAllUsers() {
        List<User> myUsers = userService.findAll();
        return new ResponseEntity<>(myUsers, HttpStatus.OK);
    }

    //Get user and its associated Role(s)
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @GetMapping(value = "/user/{userId}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long userId) {
        User u = userService.findUserById(userId);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    //Get user and its associated Role(s)
//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @GetMapping(value = "/user/name/{userName}", produces = "application/json")
//    public ResponseEntity<?> getUserByName(@PathVariable String userName) {
//        User u = userService.findByName(userName);
//        return new ResponseEntity<>(u, HttpStatus.OK);
//    }

    //Get user(s) by 'Containing' and their associated Role(s)
//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @GetMapping(value = "/user/name/like/{userName}", produces = "application/json")
//    public ResponseEntity<?> getUserLikeName(@PathVariable String userName) {
//        List<User> u = userService.findByNameContaining(userName);
//        return new ResponseEntity<>(u, HttpStatus.OK);
//    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/user", consumes = "application/json")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException {
        newuser.setUserId(0);
        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userid}").buildAndExpand(newuser.getUserid()).toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PutMapping(value = "/user/{userid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullUser(
            @Valid
            @RequestBody
                    User updateUser,
            @PathVariable
                    long userid)
    {
        updateUser.setUserId(userid);
        userService.save(updateUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Update User's Info
    @PatchMapping(value = "/user/{id}",
            consumes = "application/json")
    public ResponseEntity<?> updateUser(
            @RequestBody
                    User updateUser,
            @PathVariable
                    long id)
    {
        userService.update(updateUser,
                id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Delete User
    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(
            @PathVariable
                    long id)
    {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //Get current user and its associated role(s)
//    @GetMapping(value = "/userinfo", produces = {"application/json"})
//    public ResponseEntity<?> getCurrentUserInfo() {
//        String uname = SecurityContextHolder.getContext().getAuthentication().getName();
//        User u = userService.findByName(uname);
//        return new ResponseEntity<>(u, HttpStatus.OK);
//    }

    //Checks if Username is available
//    @GetMapping(value = "/user/exist/username/{userName}",
//            produces = "application/json")
//    public ResponseEntity<?> isUsernameExist(@PathVariable String userName) {
//        User u = userService.findByName(userName);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    //Checks if Email is available
//    @GetMapping(value = "/user/exist/email/{email}",
//            produces = "application/json")
//    public ResponseEntity<?> isEmailExist(@PathVariable String email) {
//        User u = userService.findByUserEmail(email);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}

