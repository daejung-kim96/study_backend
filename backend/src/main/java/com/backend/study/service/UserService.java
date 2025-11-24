package com.backend.study.service;

import com.backend.study.dto.request.UserReq;
import com.backend.study.dto.response.UserRes;

public interface UserService {
    UserRes getUser(UserReq req);
}
