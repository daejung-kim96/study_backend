package com.backend.study.controller;

import com.backend.study.dto.request.UserRequest;
import com.backend.study.dto.response.UserResponse;
import com.backend.study.entity.UserEntity;
import com.backend.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userServiceImpl;

    //조회
    @GetMapping("/{id}")
    public UserResponse getUser(@PathVariable("id") Long id){
        return UserResponse.from(userServiceImpl.getUser(id));
    }

    //유저 전체조회
    @GetMapping("/all")
    public List<UserEntity> getUsers(){
        return userServiceImpl.getUsers();
    }

    //유저 생성
    @PostMapping("/")
    public void createUser(@RequestBody UserRequest Req){
        userServiceImpl.createUser(Req);
    }

    //유저 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userServiceImpl.deleteUser(id);
    }

    //유저 업데이트
    @PutMapping("/{id}")
    public void updateUser(@PathVariable("id") Long id, @RequestBody UserRequest Req){
        UserResponse.from(userServiceImpl.updateUser(id, Req));

    }
}
