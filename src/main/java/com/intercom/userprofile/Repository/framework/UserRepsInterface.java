package com.intercom.userprofile.Repository.framework;

import com.intercom.userprofile.entity.RegisterRequestBody;
import com.intercom.userprofile.entity.UpdateRequestBody;
import com.intercom.userprofile.model.User;

public interface UserRepsInterface {
 int deleteUser(String userName);
 User getUserByUserName(String userName);
 int insertUser(RegisterRequestBody registerRequestBody);
 int updateUser(UpdateRequestBody updateRequestBody);

 int userCount();


}
