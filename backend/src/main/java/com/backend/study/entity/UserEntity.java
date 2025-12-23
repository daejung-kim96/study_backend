package com.backend.study.entity;

import com.backend.study.dto.request.SignupRequest;
import com.backend.study.dto.request.UserUpdateRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
}
