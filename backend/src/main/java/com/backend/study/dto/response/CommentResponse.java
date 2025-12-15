package com.backend.study.dto.response;

import com.backend.study.entity.CommentEntity;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentResponse {
    private Long id;
    private Long boardId;
    private Long userId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static CommentResponse from(CommentEntity c) {
        return CommentResponse.builder()
                .id(c.getId())
                .boardId(c.getBoard().getId())
                .userId(c.getUser().getId())
                .content(c.getContent())
                .createdAt(c.getCreatedAt())
                .updatedAt(c.getUpdatedAt())
                .build();
    }
}