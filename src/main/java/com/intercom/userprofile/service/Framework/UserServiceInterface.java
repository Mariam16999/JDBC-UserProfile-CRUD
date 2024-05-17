package com.intercom.userprofile.service.Framework;

import com.intercom.userprofile.entity.RegisterRequestBody;
import com.intercom.userprofile.entity.UpdateRequestBody;
import com.intercom.userprofile.model.User;
import com.intercom.userprofile.model.deleteuserResponse;
import com.intercom.userprofile.model.registerationResponse;
import com.intercom.userprofile.model.UpdateUserInfoResponse;


public interface UserServiceInterface {
    User login(String userName, String password) throws Exception;
    registerationResponse register(RegisterRequestBody registerRequestBody) throws Exception;
    deleteuserResponse deleteUser (String userName) throws Exception;
    UpdateUserInfoResponse updateUserInfo(UpdateRequestBody updateRequestBody)throws  Exception;

}
