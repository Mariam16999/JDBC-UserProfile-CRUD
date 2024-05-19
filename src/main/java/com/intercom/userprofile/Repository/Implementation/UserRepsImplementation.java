package com.intercom.userprofile.Repository.Implementation;

import com.intercom.userprofile.Repository.framework.UserRepsInterface;
import com.intercom.userprofile.entity.RegisterRequestBody;
import com.intercom.userprofile.entity.UpdateRequestBody;
import com.intercom.userprofile.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class UserRepsImplementation implements UserRepsInterface {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int deleteUser(String userName) {
     return jdbcTemplate.update("delete FROM user_profile WHERE username=?", userName);
    }

    @Override
    public User getUserByUserName(String userName) {
        try {
            return jdbcTemplate.queryForObject("select * from user_profile where username=?", new Object[]{userName},
                    (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("mobile"), rs.getString("address"), rs.getString("job_title"), rs.getString("password"), rs.getString("nid")));
        }
        catch (EmptyResultDataAccessException e) {
            // Handle the case where userName doesn't exist
            // For example, you can return null or throw a custom exception
            return null;
        }
    }


    @Override
    public int insertUser(RegisterRequestBody registerRequestBody) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO user_profile (`name`, `address`, `job_title`, `nid`, `username`, `password`, `mobile`) VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, registerRequestBody.getName());
            ps.setString(2, registerRequestBody.getAddress());
            ps.setString(3, registerRequestBody.getJobTitle());
            ps.setString(4, registerRequestBody.getNid());
            ps.setString(5, registerRequestBody.getUserName());
            ps.setString(6, registerRequestBody.getPassword());
            ps.setString(7, registerRequestBody.getMobileNumber());
            return ps;
        }, keyHolder);

        // Retrieve the generated key
        return keyHolder.getKey().intValue();
    }

    @Override
    public int updateUser(UpdateRequestBody UpdateRequestBody) {
        return jdbcTemplate.update("UPDATE user_profile SET name=?, address=?, job_title=?, nid=?,  password=?, mobile=? WHERE username=? ",
                UpdateRequestBody.getName(), UpdateRequestBody.getAddress(), UpdateRequestBody.getJobTitle(), UpdateRequestBody.getNid(), UpdateRequestBody.getPassword(), UpdateRequestBody.getMobileNumber(), UpdateRequestBody.getUserName());
    }

    @Override
    public int userCount() {
        return jdbcTemplate.queryForObject("select count(*) from user_profile", Integer.class);
    }
}
