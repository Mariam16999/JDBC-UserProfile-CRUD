package com.intercom.userprofile.model;

import lombok.Data;

@Data
public class UpdateUserInfoResponse {
    private String msg;
    private String name;
    private String address;
    private String userName;
    private String password;
    private String mobileNumber;
    private String jobTitle;
    private String nid;
}
