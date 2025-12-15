package com.backend.study.controller;

import com.backend.study.dto.request.CommentCreateRequest;
import com.backend.study.dto.request.CommentUpdateRequest;
import com.backend.study.dto.response.CommentResponse;
import com.backend.study.entity.CommentEntity;
import com.backend.study.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api") // 특정 게시글 댓글 불러오는 기능때문에 /api/comments를 몬함
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/comments")
    public ResponseEntity<CommentResponse> createComment(
            @RequestParam("userId") Long userId,
            @RequestBody CommentCreateRequest req
    ) {
        CommentEntity saved = commentService.createComment(req, userId);
        return new ResponseEntity<>(CommentResponse.from(saved), HttpStatus.CREATED);
    }

    // 댓글 단건 조회
    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> getComment(@PathVariable("id") Long id) {
        CommentEntity comment = commentService.getComment(id);
        return ResponseEntity.ok(CommentResponse.from(comment));
    }

    // 특정 게시글의 댓글 목록 조회
    @GetMapping("/boards/{boardId}/comments")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable("boardId") Long boardId) {
        List<CommentResponse> result = commentService.getComments(boardId)
                .stream()
                .map(CommentResponse::from)
                .toList();
        return ResponseEntity.ok(result);
    }

    // 댓글 수정
    @PutMapping("/comments/{id}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable("id") Long id,
            @RequestParam("userId") Long userId,
            @RequestBody CommentUpdateRequest req
    ) {
        CommentEntity updated = commentService.updateComment(id, req, userId);
        return ResponseEntity.ok(CommentResponse.from(updated));
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable("id") Long id,
            @RequestParam("userId") Long userId
    ) {
        commentService.deleteComment(id, userId);
        return ResponseEntity.noContent().build();
    }
}
