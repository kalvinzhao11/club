package com.kal.club.Service;

import com.kal.club.Exception.ResourceNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service(value = "helperFunctions")
public class HelperFunctionsImpl implements HelperFunctions {


    @Override
    public boolean isAuthorizedToMakeChange(String username) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(username.equalsIgnoreCase(authentication.getName()) || authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }
        else {
            throw new ResourceNotFoundException("not authorized to make changes");
        }
    }

    @Override
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication.getAuthorities().contains(
                new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return true;
        }
        else {
            return false;
        }
    }

}
