package com.intercom.userprofile.Controllers.advice;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorValidationResponse {
    /*
     * this attribute holds the validation errors as a map contains the field name as the key and the error as the value
     * */
    @Schema(description = "map of validation errors")
    private Map<String,String> validationErrors;
}