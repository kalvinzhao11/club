package com.kal.club.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserDTO {

    private Integer userId;

    private String fname;

    private String mname;

    private String lname;

    private String email;

    private String password;

    public UserDTO() {

    }

    public UserDTO(Integer userId, String fname, String mname, String lname, String email, String password) {
        this.userId = userId;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.email = email;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
