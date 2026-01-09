package com.backend.study.controller;


import com.backend.study.dto.response.UserResponse;
import com.backend.study.entity.Role;
import com.backend.study.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserService userService;

    // 권한 변경 API
    @PatchMapping("/{id}/role")
    public UserResponse changeRole(@PathVariable Long id, @RequestParam Role role) {
        return UserResponse.from(userService.changeUserRole(id, role));
    }
}
