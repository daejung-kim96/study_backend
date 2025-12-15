package com.backend.study.service;

import com.backend.study.dto.request.UserRequest;
import com.backend.study.entity.UserEntity;

import java.util.List;

public interface UserService {

    //생성
    UserEntity createUser(UserRequest Req);

    //조회
    UserEntity getUser(Long id);

    List<UserEntity> getUsers();

    //수정
    UserEntity updateUser(Long id, UserRequest Req);

    //삭제
    void deleteUser(Long id);



}
