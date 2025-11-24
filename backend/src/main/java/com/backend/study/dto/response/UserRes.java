package com.backend.study.dto.response;

import com.backend.study.entity.UserEntity;
import lombok.Data;

@Data
public class UserRes {
    private String id;

    private String userName;

    public static UserRes from(UserEntity userEntity) {
        UserRes userRes = new UserRes();
        userRes.id= userRes.getId();
        userRes.userName = userEntity.getUserName();
        return userRes;
    }
}
