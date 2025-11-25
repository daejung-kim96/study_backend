package com.backend.study.dto.request;


import lombok.Data;

@Data
public class UserCreateReq {

    private String id;

    private String password;

    private String userName;

}
