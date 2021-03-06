package com.kal.club.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class UserDTO {

    private String fname;

    private String mname;

    private String lname;

    private String username;

    @NotNull
    @Pattern(regexp = "[a-zA-z09._]+@[a-zA-Z]{2,}\\.[a-zA-Z.]+", message = "{invalid.email.format}")
    private String password;

    public UserDTO() {

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
