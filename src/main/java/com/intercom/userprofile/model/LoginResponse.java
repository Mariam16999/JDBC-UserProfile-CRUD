package com.intercom.userprofile.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private User user;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<history> history;
}
