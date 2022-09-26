package com.boot.mybatis20220923.dto;

import com.boot.mybatis20220923.domain.User;
import lombok.Data;

@Data
public class SignupReqDto {
    private String userId;
    private String userPassword;
    private String userName;
    private String userEmail;


    public User Entity() {
        return User.builder()
                .user_id(userId)
                .user_password(userPassword)
                .user_name(userName)
                .user_email(userEmail)
                .build();
    }
}
