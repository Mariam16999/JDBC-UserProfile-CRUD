package com.intercom.userprofile.Utils;


public class AccountHistoryRequest {
    public  String generateXmlRequest(String FunctionId,String ChannelCode,String HostModeFlag,String UserNumber)
    {
        String xmlRequestBody = "<RetrieveUserProfileRequest>" +
                "<FunctionId>" + FunctionId + "</FunctionId>" +
                "<ChannelCode>" + ChannelCode + "</ChannelCode>" +
                "<ReferenceId>" + GenerateRefId.generateRefNum() + "</ReferenceId>" +
                "<HostModeFlag>" + HostModeFlag + "</HostModeFlag>" +
                "<UserNumber>" + UserNumber + "</UserNumber>" +
                "</RetrieveUserProfileRequest>";

        return xmlRequestBody;
    }
}
