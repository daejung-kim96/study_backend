package com.backend.study.entity;

import com.backend.study.dto.request.UserRequest;
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
@ToString
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
    @ToString.Exclude
    private List<BoardEntity> boards = new ArrayList<>();

    // Setter 대신 쓰는 static 함수
    public static UserEntity from(UserRequest Req) {
        UserEntity NewUser = new UserEntity();
        NewUser.loginId = Req.getLoginId();
        NewUser.password = Req.getPassword();
        NewUser.userName = Req.getUserName();

        return NewUser;
    }

    // 업데이트 함수
    public void update(UserRequest Req) {
        this.loginId = Req.getLoginId();
        this.password = Req.getPassword();
        this.userName = Req.getUserName();
    }
}
