package com.intercom.userprofile.service.Implementation;

import com.intercom.userprofile.Repository.framework.UserRepsInterface;
import com.intercom.userprofile.entity.RegisterRequestBody;
import com.intercom.userprofile.entity.UpdateRequestBody;
import com.intercom.userprofile.model.User;
import com.intercom.userprofile.model.deleteuserResponse;
import com.intercom.userprofile.model.registerationResponse;
import com.intercom.userprofile.model.UpdateUserInfoResponse;
import com.intercom.userprofile.service.Framework.UserServiceInterface;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import io.undertow.util.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.webjars.NotFoundException;


@Component
public class UserServiceImplementation implements UserServiceInterface {

    private final UserRepsInterface userRepsInterface;

    @Autowired
    public UserServiceImplementation(UserRepsInterface userRepsInterface) {
        this.userRepsInterface = userRepsInterface;
    }


    public deleteuserResponse deleteUser(String userName) throws Exception {
        deleteuserResponse deleteuserResponse = new deleteuserResponse();
        if (userRepsInterface.deleteUser(userName) == 0) {
            throw new NotFoundException("This user does not exist");
        }
        deleteuserResponse.setMsg("User deleted successfully");
        deleteuserResponse.setUserName(userName);
        return deleteuserResponse;
    }

    @Override
    public UpdateUserInfoResponse updateUserInfo(UpdateRequestBody updateRequestBody) throws Exception {
        if(updateRequestBody ==null)
        {
            throw new BadRequestException("Invalid Data");
        }

        if(updateRequestBody.getUserName()==null|| updateRequestBody.getUserName().isEmpty() )
        {
            throw new BadRequestException("Invalid Mail");
        }
        if(updateRequestBody.getName()==null|| updateRequestBody.getName().isEmpty())
        {
            throw new BadRequestException("Invalid Name");
        }
        if(updateRequestBody.getAddress()==null|| updateRequestBody.getAddress().isEmpty())
        {
            throw new BadRequestException("Invalid Address");
        }
        if(updateRequestBody.getJobTitle()==null|| updateRequestBody.getJobTitle().isEmpty())
        {
            throw new BadRequestException("Invalid JobTitle");
        }
        if(updateRequestBody.getNid()==null|| updateRequestBody.getNid().isEmpty())
        {
            throw new BadRequestException("Invalid Nid");
        }
        if(updateRequestBody.getMobileNumber()==null|| updateRequestBody.getMobileNumber().isEmpty())
        {
            throw new BadRequestException("Invalid MobileNumber");
        }
        if(updateRequestBody.getPassword()==null|| updateRequestBody.getPassword().isEmpty())
        {
            throw new BadRequestException("Invalid password");
        }
        if (!(updateRequestBody.getMobileNumber().matches("00201[0-2,5]{1}[0-9]{8}")) && !(updateRequestBody.getMobileNumber().matches("01[0-2,5]{1}[0-9]{8}")))
        {
            throw new BadRequestException("Invalid mobile Number");
        }
        if(!updateRequestBody.getNid().matches("^([1-9]{1})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})[0-9]{3}([0-9]{1})[0-9]{1}$"))
        {
            throw new BadRequestException("Invalid NID");
        }
        if (userRepsInterface.updateUser(updateRequestBody) == 0) {
            throw new NotFoundException("User not found");
        }
        UpdateUserInfoResponse updateUserInfoResponse = new UpdateUserInfoResponse();
        updateUserInfoResponse.setMsg("User Updated successfully");
        updateUserInfoResponse.setUserName(updateRequestBody.getUserName());
        updateUserInfoResponse.setAddress(updateRequestBody.getAddress());
        updateUserInfoResponse.setNid(updateRequestBody.getNid());
        updateUserInfoResponse.setName(updateRequestBody.getName());
        updateUserInfoResponse.setPassword(updateRequestBody.getPassword());
        updateUserInfoResponse.setJobTitle(updateRequestBody.getJobTitle());
        updateUserInfoResponse.setMobileNumber(updateRequestBody.getMobileNumber());
        return updateUserInfoResponse;
    }


    @Override
    public User login(String userName, String password) throws Exception {
        User userFromDb = userRepsInterface.getUserByUserName(userName);
        if (userFromDb == null) {
            throw new NotFoundException("This userName doesn't exist");
        }
        if (!userFromDb.getPassword().equals(password)) {
            throw new UnauthorizedException("You Shall Not pass!!");
        }
        return userRepsInterface.getUserByUserName(userName);

    }

    @Override
    public registerationResponse register(RegisterRequestBody registerRequestBody) throws Exception {
//        if(registerRequestBody ==null)
//        {
//            throw new BadRequestException("Invalid Data");
//        }
//
//        if(registerRequestBody.getUserName()==null|| registerRequestBody.getUserName().isEmpty() )
//        {
//            throw new BadRequestException("Invalid Mail");
//        }
//        if(registerRequestBody.getName()==null|| registerRequestBody.getName().isEmpty())
//        {
//            throw new BadRequestException("Invalid Name");
//        }
//        if(registerRequestBody.getAddress()==null|| registerRequestBody.getAddress().isEmpty())
//        {
//            throw new BadRequestException("Invalid Address");
//        }
//        if(registerRequestBody.getJobTitle()==null|| registerRequestBody.getJobTitle().isEmpty())
//        {
//            throw new BadRequestException("Invalid JobTitle");
//        }
//        if(registerRequestBody.getNid()==null|| registerRequestBody.getNid().isEmpty())
//        {
//            throw new BadRequestException("Invalid Nid");
//        }
//        if(registerRequestBody.getMobileNumber()==null|| registerRequestBody.getMobileNumber().isEmpty())
//        {
//            throw new BadRequestException("Invalid MobileNumber");
//        }
//        if(registerRequestBody.getPassword()==null|| registerRequestBody.getPassword().isEmpty())
//        {
//            throw new BadRequestException("Invalid password");
//        }
//        if (!(registerRequestBody.getMobileNumber().matches("00201[0-2,5]{1}[0-9]{8}")) && !(registerRequestBody.getMobileNumber().matches("01[0-2,5]{1}[0-9]{8}")))
//        {
//            throw new BadRequestException("Invalid mobile Number");
//        }
//        if(!registerRequestBody.getNid().matches("^([1-9]{1})([0-9]{2})([0-9]{2})([0-9]{2})([0-9]{2})[0-9]{3}([0-9]{1})[0-9]{1}$"))
//        {
//            throw new BadRequestException("Invalid NID");
//        }
        registerationResponse registerationResponse = new registerationResponse();
        if (userRepsInterface.getUserByUserName(registerRequestBody.getUserName()) == null) {
            registerationResponse.setId(userRepsInterface.insertUser(registerRequestBody));
            registerationResponse.setMsg("User Registered successfully");
            registerationResponse.setUserName(registerRequestBody.getUserName());
            return registerationResponse;
        }

        throw new ForbiddenException("This userName is already used");

    }
}
