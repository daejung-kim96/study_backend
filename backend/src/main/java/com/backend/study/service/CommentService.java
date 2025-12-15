package com.backend.study.service;

import com.backend.study.dto.request.CommentCreateRequest;
import com.backend.study.dto.request.CommentUpdateRequest;
import com.backend.study.entity.CommentEntity;

import java.util.List;

public interface CommentService {

    //생성
    CommentEntity createComment(CommentCreateRequest Req, Long userId);

    //단건 조회
    CommentEntity getComment(Long id);

    //리스트 조회
    List<CommentEntity> getComments(Long boardId);

    //수정
    CommentEntity updateComment(Long id, CommentUpdateRequest Req, Long userId);

    //삭제
    void deleteComment(Long id, Long userId);
}
