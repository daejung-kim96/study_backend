package com.backend.study.serviceImpl;

import com.backend.study.dto.request.UserCreateReq;
import com.backend.study.entity.UserEntity;
import com.backend.study.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public void createUser(UserCreateReq Req) {
        UserEntity NewUser = new UserEntity();
        NewUser.createUser(Req);
    }

    @Override
    public void updateUser(UserEntity userEntity) {

    }

    @Override
    public void deleteUser(UserEntity userEntity) {

    }

    @Override
    public UserEntity findUserById(Long id) {
        return null;
    }
}
