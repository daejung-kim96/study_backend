package com.backend.study.service;


import com.backend.study.dto.request.BoardCreateRequest;
import com.backend.study.dto.request.BoardUpdateRequest;
import com.backend.study.entity.BoardEntity;

import java.util.List;

public interface BoardService {

    //생성
    BoardEntity createBoard(BoardCreateRequest Req);

    //단건 조회
    BoardEntity getBoard(Long id);

    //리스트 조회
    List<BoardEntity> getBoards(Long userId);

    //수정
    BoardEntity updateBoard(Long id, BoardUpdateRequest Req);

    //삭제
    void deleteBoard(Long id);
}
