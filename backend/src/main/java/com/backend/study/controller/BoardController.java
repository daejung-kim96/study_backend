package com.backend.study.controller;

import com.backend.study.dto.request.BoardCreateRequest;
import com.backend.study.dto.request.BoardUpdateRequest;
import com.backend.study.dto.response.BoardResponse;
import com.backend.study.entity.BoardEntity;
import com.backend.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardController {

    private final BoardService boardService;
    
    //게시글 생성
    @PostMapping
    public ResponseEntity<BoardResponse> createBoard(@RequestBody BoardCreateRequest request) {
        BoardEntity board = boardService.createBoard(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BoardResponse.from(board));
    }

    // 게시글 단건 조회
    @GetMapping("/{id}")
    public ResponseEntity<BoardResponse> getBoard(@PathVariable Long id) {
        BoardEntity board = boardService.getBoard(id);
        return ResponseEntity.ok(BoardResponse.from(board));
    }

    // 특정유저의 게시글 전체 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BoardResponse>> getBoardsByUser(@PathVariable Long userId) {
        List<BoardEntity> boards = boardService.getBoards(userId);
        List<BoardResponse> responseList = boards.stream()
                .map(BoardResponse::from)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseList);
    }

    // 특정 게시글 수정
    @PutMapping("/{id}")
    public ResponseEntity<BoardResponse> updateBoard(
            @PathVariable Long id,
            @RequestBody BoardUpdateRequest request
    ) {
        BoardEntity updated = boardService.updateBoard(id, request);
        return ResponseEntity.ok(BoardResponse.from(updated));
    }

    // 특정 게시글 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
    }
}
