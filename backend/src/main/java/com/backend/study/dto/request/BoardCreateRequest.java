package com.backend.study.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BoardCreateRequest {

    private Long userId;

    private String title;

    private String content;

}
