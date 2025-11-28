package com.backend.study.serviceImpl;

import com.backend.study.dto.request.UserRequest;
import com.backend.study.dto.response.UserResponse;
import com.backend.study.entity.UserEntity;
import com.backend.study.repository.UserRepository;
import com.backend.study.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity createUser(UserRequest Req) {
        UserEntity user = UserEntity.from(Req);

        UserEntity savedUser = userRepository.save(user);
        log.info("created User : {} ", savedUser.getId());

        return savedUser;

    }

    @Override
    public UserEntity getUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id : {}", id);
                    return new RuntimeException("User not found with id : " + id);
                });
    }

    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public UserEntity updateUser(Long id, UserRequest Req) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        user.update(Req);
        log.info(user.toString());

        return user;

    }

    @Override
    public void deleteUser(Long userNo) {
        userRepository.deleteById(userNo);
    }

}
