package com.intercom.userprofile.service.Implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercom.userprofile.Repository.framework.UserRepsInterface;
import com.intercom.userprofile.Utils.GenerateRefId;
import com.intercom.userprofile.Utils.RetrieveUserProfileRequest;
import com.intercom.userprofile.entity.RegisterRequestBody;
import com.intercom.userprofile.entity.UpdateRequestBody;
import com.intercom.userprofile.model.*;
import com.intercom.userprofile.service.Framework.UserServiceInterface;
import io.quarkus.security.ForbiddenException;
import io.quarkus.security.UnauthorizedException;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.webjars.NotFoundException;
import org.xml.sax.InputSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;


@Component
@Log4j2
public class UserServiceImplementation implements UserServiceInterface {

    private final UserRepsInterface userRepsInterface;
    private final RestTemplate restTemplate = new RestTemplate();

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

    @Value( "${RetrieveUserProfile.uri}")
    private String retrieveUserProfileUrl;

    @Value( "${AccountHistory.uri}")
    private String accountHistoryUrl;
    @Override
    public LoginResponse login(String userName, String password) throws Exception {
        String responseEntity="";
        LoginResponse loginResponse=new LoginResponse();
        log.info("loginRequest"+userName +"  "+ password);
        User userFromDb = userRepsInterface.getUserByUserName(userName);
        if (userFromDb == null) {
            throw new NotFoundException("This userName doesn't exist");
        }
        if (!userFromDb.getPassword().equals(password)) {
            throw new UnauthorizedException("You Shall Not pass!!");
        }
        User user=userRepsInterface.getUserByUserName(userName);

        //calling RetrieveUserProfileRequest to check UserNumber
        RetrieveUserProfileRequest retrieveUserProfileRequest = new RetrieveUserProfileRequest();
        String xmlRequest=retrieveUserProfileRequest.generateXmlRequest("300","20","01",""+user.getId());
        HttpHeaders headers = new HttpHeaders();
        URI uri=null;
       try {
           uri=new URI(retrieveUserProfileUrl);
       } catch (URISyntaxException e) {
           throw new NotFoundException("Service is currently not available");
       }
       headers.add("Content-Type",MediaType.APPLICATION_XML_VALUE);
       headers.add("Accept",MediaType.APPLICATION_XML_VALUE);
       try {
           log.info("mockRequest"+xmlRequest);
           RequestEntity requestEntity=new RequestEntity<>(xmlRequest,headers, HttpMethod.POST,URI.create(retrieveUserProfileUrl));
            responseEntity=restTemplate.exchange(requestEntity,String.class).getBody();
           log.info("mockResponse"+responseEntity);
       } catch (Exception e) {
           throw new TimeoutException( "TimeOut:Service is currently not available");
       }
       //Convert String to XML

        // Create a DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        assert responseEntity != null;
        Document document = builder.parse(new InputSource(new StringReader(responseEntity)));
        String customerAccount=document.getElementsByTagName("CustomerAccount").item(0).getTextContent();
        log.info("customerAccount"+customerAccount);

        //Call AccountHistory
        uri=null;
        try {
            uri=new URI(accountHistoryUrl);
        } catch (URISyntaxException e) {
            throw new NotFoundException("Service is currently not available");
        }
        headers.add("Content-Type", "application/json; charset=utf-8");
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        JSONObject parameters = new JSONObject();
        parameters.put("customerAccount",customerAccount );
        parameters.put("referenceId", GenerateRefId.generateRefNum());
        RequestEntity requestEntity;
         responseEntity="";
        JSONObject jsonResponse;
        try {
            log.info("AccountHistoryRequest:"+parameters);
            requestEntity = new RequestEntity(parameters.toString(), headers, HttpMethod.POST, URI.create(accountHistoryUrl));
            responseEntity = restTemplate.exchange(requestEntity, String.class).getBody();
        } catch (Exception e) {
            throw new TimeoutException( "Service is currently not available");
        }
        log.info("AccountHistoryResponse:"+responseEntity);
        //getting response from json
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> responseMap = mapper.readValue(responseEntity, Map.class);
        Object history = responseMap.get("history");
        log.info("history"+history);
         loginResponse.setHistory((List<com.intercom.userprofile.model.history>) history);
        loginResponse.setUser(user);
        log.info("loginResponse"+loginResponse);
        return loginResponse;

    }

    @Override
    public registerationResponse register(RegisterRequestBody registerRequestBody) throws Exception {

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
