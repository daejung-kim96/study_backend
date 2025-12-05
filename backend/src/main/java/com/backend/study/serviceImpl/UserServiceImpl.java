package com.backend.study.serviceImpl;

import com.backend.study.dto.request.UserRequest;
import com.backend.study.dto.response.UserResponse;
import com.backend.study.entity.UserEntity;
import com.backend.study.repository.UserRepository;
import com.backend.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    //생성
    @Override
    @Transactional
    public UserEntity createUser(UserRequest Req) {
        UserEntity user = UserEntity.from(Req);

        UserEntity savedUser = userRepository.save(user);
        log.info("created User : {} ", savedUser.getId());

        return savedUser;

    }
    
    //조회
    @Override
    public UserEntity getUser(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id : {}", id);
                    return new RuntimeException("User not found with id : " + id);
                });
    }

    //유저 전체 조회 <- 근데 얘 필요한거 맞아? 아직 구현은 제대로 못함
    @Override
    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public UserEntity updateUser(Long id, UserRequest Req) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        user.update(Req);
        log.info(user.toString());

        return user;

    }

    //삭제
    @Transactional
    @Override
    public void deleteUser(Long userNo) {
        userRepository.deleteById(userNo);
    }

}
