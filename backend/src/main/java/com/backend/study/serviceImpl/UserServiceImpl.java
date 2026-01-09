package com.backend.study.serviceImpl;

import com.backend.study.dto.request.SignupRequest;
import com.backend.study.dto.request.UserUpdateRequest;
import com.backend.study.entity.Role;
import com.backend.study.entity.UserEntity;
import com.backend.study.repository.UserRepository;
import com.backend.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Override
    @Transactional
    public UserEntity createUser(SignupRequest req) {
        UserEntity user = UserEntity.from(req);

        user.changePassword(passwordEncoder.encode(req.getPassword()));

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

    //수정
    @Override
    @Transactional
    public UserEntity updateUser(Long id, UserUpdateRequest req) {
        UserEntity user = userRepository.findById(id).orElseThrow();

        user.update(req);

        return user;
    }

    // 2026. 1월 첫째주 추가 -> 권한 변경 메서드
    @Override
    @Transactional
    public UserEntity changeUserRole(Long id, Role role) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("코런 유저는 없어용 : {}", id);
                    return new RuntimeException("코런 유저는 없어용 : " + id);
                });

        user.changeRole(role);
        log.info("changed role. userId={}, newRole={}", id, role);

        return user;
    }
    

    //삭제
    @Transactional
    @Override
    public void deleteUser(Long userNo) {
        userRepository.deleteById(userNo);
    }

}
