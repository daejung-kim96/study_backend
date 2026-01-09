package com.backend.study.entity;

import com.backend.study.dto.request.SignupRequest;
import com.backend.study.dto.request.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id" , nullable = false)
    private Long id;

    @Column(name = "login_id" , nullable = false)
    private String loginId;

    @Column(name = "password" , nullable = false)
    private String password;

    @Column(name = "user_name" , nullable = false)
    private String userName;


    // 2026. 1월 첫째주 추가 -> role 추가
    @Enumerated(EnumType.STRING)
    @Column(name = "role" , nullable = false)
    private Role role = Role.GUEST;

    // 게시판 글 들이랑 양방향 연결
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<BoardEntity> boards = new ArrayList<>();

    // Setter 대신 쓰는 static 함수
    public static UserEntity from(SignupRequest Req) {
        UserEntity NewUser = new UserEntity();
        NewUser.loginId = Req.getLoginId();
        // NewUser.password = Req.getPassword();  서비스에서 암호화 후 주입으로 변경
        NewUser.userName = Req.getUserName();
        NewUser.role = Role.GUEST;

        return NewUser;
    }

    public void changePassword(String encodedPassword) {
        this.password = encodedPassword;
    }

    // 업데이트 함수
    public void update(UserUpdateRequest req) {
        // this.password = Req.getPassword(); 얘도 마찬가지
        this.userName = req.getUserName();
    }

    // 유저에 role이 지정이 안됐을 때 강제로 게스트로 할당
    @PrePersist
    public void prePersist() {
        if (this.role == null) this.role = Role.GUEST;
    }

    // 2026. 1월 첫째주 추가 -> role 변경 함수
    public void changeRole(Role role) {
        this.role = role;
    }
}
