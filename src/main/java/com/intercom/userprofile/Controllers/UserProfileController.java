package com.intercom.userprofile.Controllers;

import com.intercom.userprofile.entity.RegisterRequestBody;
import com.intercom.userprofile.entity.UpdateRequestBody;
import com.intercom.userprofile.model.UpdateUserInfoResponse;
import com.intercom.userprofile.model.User;
import com.intercom.userprofile.model.deleteuserResponse;
import com.intercom.userprofile.model.registerationResponse;
import com.intercom.userprofile.service.Framework.UserServiceInterface;
import io.undertow.util.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserProfileController {
   private static final Logger log=LogManager.getLogger(UserProfileController.class);
    @Autowired
    private UserServiceInterface userServiceInterface;
    @PostMapping("/register")
    public registerationResponse register(@Valid @RequestBody RegisterRequestBody registerRequestBody) throws Exception
    {
        log.info("Register input:"+ registerRequestBody);
        return userServiceInterface.register(registerRequestBody);
    }
   @PutMapping("/updateUserInfo")
    public UpdateUserInfoResponse updateUserInfo(@RequestBody UpdateRequestBody  updateRequestBody) throws Exception
    {
        log.info("service:"+ updateRequestBody);
        return userServiceInterface.updateUserInfo(updateRequestBody);
    }

    @DeleteMapping("/deleteUser")
    public deleteuserResponse deleteUser(@RequestParam String userName) throws  Exception
    {
        return userServiceInterface.deleteUser(userName);
   }

    @GetMapping("/login")
    public User login(@RequestParam String userName, @RequestParam String password) throws Exception
    {

        return userServiceInterface.login(userName,password);
    }
}
