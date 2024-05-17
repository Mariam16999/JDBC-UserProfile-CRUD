package com.intercom.userprofile.Mapper;

import com.intercom.userprofile.entity.RegisterRequestBody;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
public class UserMapper {

//public class UserMapper implements RowMapper<RegisterRequestBody> {

//    @Override
//    public RegisterRequestBody mapRow(ResultSet rs, int rowNum) throws SQLException {
//        return new RegisterRequestBody(rs.getInt("id"),rs.getString("name"),rs.getString("username"),rs.getString("mobile"),rs.getString("address"),rs.getString("job_title"),rs.getString("password"),rs.getString("nid"));
//    }
}
