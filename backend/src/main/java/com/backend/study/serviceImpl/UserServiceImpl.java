package com.backend.study.serviceImpl;

import com.backend.study.dto.request.UserReq;
import com.backend.study.dto.response.UserRes;
import com.backend.study.entity.UserEntity;
import com.backend.study.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    @Override
    public UserRes getUser(UserReq req) {
        UserEntity entity=new UserEntity();
        entity.create(req);
        return UserRes.from(entity);
    }
}
