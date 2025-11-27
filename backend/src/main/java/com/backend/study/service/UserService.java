package com.backend.study.service;

import com.backend.study.dto.request.UserReq;
import com.backend.study.entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserService {

    //생성
    Long createUser(UserReq Req);

    //조회
    UserEntity getUser(Long userNo);

    //수정
    void updateUser(UserEntity userEntity);

    //삭제
    void deleteUser(Long id);



}
