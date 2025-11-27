package com.backend.study.controller;

import com.backend.study.dto.request.UserReq;
import com.backend.study.entity.UserEntity;
import com.backend.study.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserEntity get(@PathVariable("id") Long userNo){
        return userService.getUser(userNo);
    }

    @PostMapping("/")
    public Map<String, Long> createUser(@RequestBody UserReq Req){
        Long id = userService.createUser(Req);

        return Map.of("USER",id);
    }
}
