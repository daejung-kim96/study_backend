package com.backend.study.controller;

import com.backend.study.dto.request.LoginRequestDto;
import com.backend.study.dto.response.LoginResponseDto;
import com.backend.study.security.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    // 세션 기반
//  private final SecurityContextRepository securityContextRepository;

    // 토큰 기반
    private final JwtTokenProvider jwtTokenProvider;


    // 로그인 (세션기반)
//    @PostMapping("/login")
//    public void login(@RequestBody LoginRequestDto req,
//                      HttpServletRequest request,
//                      HttpServletResponse response) {
//
//        request.getSession(true);
//
//        Authentication auth = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(req.getLoginId(), req.getPassword())
//        );
//
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication(auth);
//
//        SecurityContextHolder.setContext(context);
//        securityContextRepository.saveContext(context, request, response);
//    }

    // 로그인 AccessToken으로 변경
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto req) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getLoginId(), req.getPassword())
        );

        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String accessToken = jwtTokenProvider.generateAccessToken(userDetails);

        return ResponseEntity.ok(new LoginResponseDto(accessToken));
    }

    //로그아웃 (세션 삭제랑 쿠키 만료까지) 세션기반
//    @PostMapping("/logout")
//    public void logout(HttpServletRequest request, HttpServletResponse response) {
//        SecurityContextHolder.clearContext();
//
//        HttpSession session = request.getSession(false);
//        if (session != null) session.invalidate();
//
//        jakarta.servlet.http.Cookie cookie = new jakarta.servlet.http.Cookie("JSESSIONID", "");
//        cookie.setPath("/");
//        cookie.setMaxAge(0);
//        response.addCookie(cookie);
//    }

    //로그 아웃 -> 토큰 삭제
    @PostMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }


}
