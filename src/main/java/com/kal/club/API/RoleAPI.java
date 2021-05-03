package com.kal.club.API;

import com.kal.club.Entity.Role;
import com.kal.club.Service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

//import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleAPI {

    @Autowired
    RoleService roleService;

    //Retrieves all Roles and its associated Users
    @GetMapping(value = "/roles", produces = "application/json")
    public ResponseEntity<?> listRoles() {
        List<Role> allRoles = roleService.findAll();
        return new ResponseEntity<>(allRoles,
                HttpStatus.OK);
    }

    //Retrieves all User associated with a Role (by ID)
    @GetMapping(value = "/role/{roleId}", produces = "application/json")
    public ResponseEntity<?> getRoleById(@PathVariable long roleId) {
        Role r = roleService.findRoleById(roleId);
        return new ResponseEntity<>(r,
                HttpStatus.OK);
    }

    //Retrieves all User associated with a Role (by roleName)
    @GetMapping(value = "/role/name/{name}",
            produces = "application/json")
    public ResponseEntity<?> getRoleByName(
            @PathVariable
                    String name)
    {
        Role r = roleService.findByName(name);
        return new ResponseEntity<>(r,
                HttpStatus.OK);
    }

    //Post a new Role
//    @PostMapping(value = "/role",
//            consumes = "application/json")
//    public ResponseEntity<?> addNewRole(@Valid @RequestBody Role newRole) {
//        // ids are not recognized by the Post method
//        newRole.setRoleid(0);
//        newRole = roleService.save(newRole);
//
//        // set the location header for the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{roleid}")
//                .buildAndExpand(newRole.getRoleid())
//                .toUri();
//        responseHeaders.setLocation(newRoleURI);
//
//        return new ResponseEntity<>(null,
//                responseHeaders,
//                HttpStatus.CREATED);
//    }

//    @PutMapping(value = "/role/{roleid}",
//            consumes = {"application/json"})
//    public ResponseEntity<?> putUpdateRole(@PathVariable Integer roleid, @Valid @RequestBody Role newRole) {
//        newRole = roleService.update(roleid,
//                newRole);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    //Delete Role
    @DeleteMapping(value = "/role/{id}")
    public ResponseEntity<?> deleteRoleById(
            @PathVariable
                    long id) {
        roleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
