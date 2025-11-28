package com.backend.study.entity;

import com.backend.study.dto.request.UserRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id")
    private String loginId;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;

    public static UserEntity from(UserRequest Req) {
        UserEntity NewUser = new UserEntity();
        NewUser.loginId = Req.getLoginId();
        NewUser.password = Req.getPassword();
        NewUser.userName = Req.getUserName();

        return NewUser;
    }

    public void update(UserRequest Req) {
        this.loginId = Req.getLoginId();
        this.password = Req.getPassword();
        this.userName = Req.getUserName();
    }
}
