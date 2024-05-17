package com.intercom.userprofile.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class BadRequestErrorResponse {
@JsonInclude(JsonInclude.Include.NON_NULL)
private String code;
@JsonInclude(JsonInclude.Include.NON_NULL)
private String message;
@JsonInclude(JsonInclude.Include.NON_NULL)
private String moreInformation;
}
