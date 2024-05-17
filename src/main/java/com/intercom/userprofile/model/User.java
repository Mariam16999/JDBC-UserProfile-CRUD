package com.intercom.userprofile.model;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class User {
    private int id;
    private String name;
    private String address;
    private String userName;
    private String password;
    private String mobileNumber;
    private String jobTitle;
    private String nid;
    public User(int id, String name, String username, String mobileNumber, String address, String jobTitle, String password, String nid) {
        this.id=id;
        this.nid=nid;
        this.userName=username;
        this.name=name;
        this.password=password;
        this.address=address;
        this.jobTitle=jobTitle;
        this.mobileNumber=mobileNumber;
    }

}
