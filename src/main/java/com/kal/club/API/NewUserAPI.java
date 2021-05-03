package com.kal.club.API;

import com.kal.club.DTO.UserDTO;
import com.kal.club.Entity.User;
import com.kal.club.Entity.UserRoles;
import com.kal.club.Service.RoleService;
import com.kal.club.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class NewUserAPI {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @PostMapping(value = "/createnewuser", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> addSelf(HttpServletRequest httpServletRequest, @RequestBody UserDTO newminuser) throws Exception{

        //create new user
        User newUser = new User();
        newUser.setFname(newminuser.getFname());
        newUser.setMname(newminuser.getMname());
        newUser.setLname(newminuser.getLname());
        newUser.setPassword(newminuser.getPassword());
        newUser.setUsername(newminuser.getUsername());

        Set<UserRoles> newRoles = new HashSet<>();
        newRoles.add(new UserRoles(newUser, roleService.findByName("MEMBER")));//
        newUser.setRoles(newRoles);
        newUser = userService.save(newUser);

        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString("localhost:" + httpServletRequest.getLocalPort() + "/users/user/{userId}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        // return the access token
        // To get the access token, surf to the endpoint /login just as if a client had done this.
        RestTemplate restTemplate = new RestTemplate();
        String requestURI = "http://localhost:" + httpServletRequest.getLocalPort() + "/login";

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(acceptableMediaTypes);
//        headers.setBasicAuth("system-client",
//                "system-secret");
        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
                System.getenv("OAUTHCLIENTSECRET"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",
                "password");
        map.add("scope",
                "read write trust");
        map.add("username",
                newminuser.getUsername());
        map.add("password",
                newminuser.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
                headers);

        String theToken = restTemplate.postForObject(requestURI,
                request,
                String.class);

        return new ResponseEntity<>(theToken,
                responseHeaders,
                HttpStatus.CREATED);
    }

}