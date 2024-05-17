package com.intercom.userprofile.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class UpdateRequestBody {



    @NotNull(message = "Name may not be null")
    @NotBlank(message = "Name is mandatory")
    @Schema(example = "Mariam Jamal")
    private String name;

    @Schema(example = "cairo,Egypt")
    @NotNull(message = "Address may not be null")
    @NotBlank (message = "Address is mandatory")
    private String address;

    @NotBlank (message = "userName is mandatory")
    @NotNull(message = "userName  may not be null")
    @Schema(example = "jemy16")
    private String userName;

    @NotBlank (message = "password is mandatory")
    @NotNull(message = "password may not be null")
    @Schema(example = "123")
    private String password;

    @NotBlank (message = "mobileNumber is mandatory")
    @NotNull(message = "mobileNumber may not be null")
    @Pattern(regexp = "^(00201|\\+201|01|1)[0-2,5]{1}[0-9]{8}$",message = "Invalid Mobile Number Dude!!!!!!!!!")
    @Schema(example = "00201013164889")
    private String mobileNumber;

    @Schema(example = "Software Engineer")
    @NotNull(message = "jobTitle may not be null")
    @NotBlank (message = "jobTitle is mandatory")
    private String jobTitle;

    @Schema(example = "29909160100688")
    @NotBlank (message = "NID is mandatory")
    @NotNull(message = "NID may not be null")
    @Pattern(regexp = "^([1-9]{1})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})[0-9]{3}([0-9]{1})[0-9]{1}$",message = "Invalid National Id Bro!!!!!!!!!!!")
    private String nid;



}
