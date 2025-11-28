package com.backend.study.dto.response;

import com.backend.study.entity.UserEntity;
import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String loginId;

    private String userName;


    public static UserResponse from(UserEntity userEntity){

        return UserResponse.builder()
                .loginId(userEntity.getLoginId())
                .userName(userEntity.getUserName())
                .build();
    }
}
