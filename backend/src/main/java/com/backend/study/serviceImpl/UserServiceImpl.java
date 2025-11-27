package com.backend.study.serviceImpl;

import com.backend.study.dto.request.UserReq;
import com.backend.study.entity.UserEntity;
import com.backend.study.repository.UserRepository;
import com.backend.study.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Long createUser(UserReq Req) {
        UserEntity NewUser = new UserEntity();
        NewUser.createUser(Req);

        return NewUser.getUserNo();
    }

    @Override
    public UserEntity getUser(Long userNo) {
        Optional<UserEntity> result = userRepository.findById(userNo);

        return result.orElseThrow();
    }

    @Override
    public void updateUser(UserEntity userEntity) {
        Optional<UserEntity> result = userRepository.findById(userEntity.getUserNo());

        UserEntity user = result.orElseThrow();

        user.changeId(userEntity.getId());
        user.changePassword(userEntity.getPassword());

        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
