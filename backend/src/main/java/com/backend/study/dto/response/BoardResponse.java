package com.backend.study.dto.response;

import com.backend.study.entity.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class BoardResponse {

    private Long id;
    private Long userId;
    private String userName;
    private String title;
    private String content;
    private LocalDateTime createdAt;

    public static BoardResponse from(BoardEntity board) {
        return BoardResponse.builder()
                .id(board.getId())
                .userId(board.getUser().getId())
                .userName(board.getUser().getUserName())
                .title(board.getTitle())
                .content(board.getContent())
                .createdAt(board.getCreatedAt())
                .build();
    }
}
