package com.backend.study.entity;

import com.backend.study.dto.request.UserReq;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "id")
    private String id;

    @Column(name = "password")
    private String password;

    @Column(name = "user_name")
    private String userName;


    public UserEntity create(UserReq req){
        UserEntity entity=new UserEntity();
        entity.id= req.getId();
        entity.password=req.getPassword();
        entity.userName=req.getUserName();

        return entity;
    }
}
