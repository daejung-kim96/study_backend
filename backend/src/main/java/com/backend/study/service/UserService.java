package com.backend.study.service;

import com.backend.study.dto.request.UserCreateReq;
import com.backend.study.entity.UserEntity;

public interface UserService {

    //생성
    void createUser(UserCreateReq Req);

    //수정
    void updateUser(UserEntity userEntity);

    //삭제
    void deleteUser(UserEntity userEntity);

    //조회
    UserEntity findUserById(Long id);

}
