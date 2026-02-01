package com.backend.study.serviceImpl;

import com.backend.study.dto.request.BoardCreateRequest;
import com.backend.study.dto.request.BoardUpdateRequest;
import com.backend.study.entity.BoardEntity;
import com.backend.study.entity.UserEntity;
import com.backend.study.repository.BoardRepository;
import com.backend.study.repository.UserRepository;
import com.backend.study.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    // 생성
    @Override
    @Transactional
    public BoardEntity createBoard(BoardCreateRequest req) {
        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("코런 유저는 없어용 loginId=" + loginId));

        BoardEntity board = BoardEntity.from(req, user);
        return boardRepository.save(board);
    }

    // 단건 조회
    @Override
    public BoardEntity getBoard(Long id) {
        return boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코런 게시글은 없어용. id=" + id));
    }

    // 유저id로 게시글 전체 목록 조회
    @Override
    public List<BoardEntity> getBoards(Long userId) {
        return boardRepository.findByUser_Id(userId);
    }

    // 수정
    @Override
    @Transactional
    public BoardEntity updateBoard(Long id, BoardUpdateRequest req) {

        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코런 게시글은 없어용. id=" + id));

        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 작성자만 허용
        if (!board.getUser().getLoginId().equals(loginId)) {
            throw new AccessDeniedException("작성자만 수정할 수 있어요.");
        }

        board.changeTitleAndContent(req.getTitle(), req.getContent());

        return board;
    }

    // 삭제
    @Override
    @Transactional
    public void deleteBoard(Long id) {
        BoardEntity board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("코런 게시글은 없어용. id=" + id));

        String loginId = SecurityContextHolder.getContext().getAuthentication().getName();

        // 작성자만 허용
        if (!board.getUser().getLoginId().equals(loginId)) {
            throw new AccessDeniedException("작성자만 삭제할 수 있어요.");
        }

        boardRepository.delete(board);
    }
}
