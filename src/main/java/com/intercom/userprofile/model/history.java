package com.intercom.userprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class history {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double amount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Character action;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String desc;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fullDesc;
}
