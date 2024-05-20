package com.intercom.userprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class User {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private int id;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String address;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String password;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mobileNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String jobTitle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
