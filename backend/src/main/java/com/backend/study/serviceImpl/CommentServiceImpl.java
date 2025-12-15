package com.backend.study.serviceImpl;

import com.backend.study.dto.request.CommentCreateRequest;
import com.backend.study.dto.request.CommentUpdateRequest;
import com.backend.study.entity.BoardEntity;
import com.backend.study.entity.CommentEntity;
import com.backend.study.entity.UserEntity;
import com.backend.study.repository.BoardRepository;
import com.backend.study.repository.CommentRepository;
import com.backend.study.repository.UserRepository;
import com.backend.study.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public CommentEntity createComment(CommentCreateRequest req, Long userId) {

        BoardEntity board = boardRepository.findById(req.getBoardId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "코런 게시글은 없어용"));

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "코런 유저는 없어용"));

        CommentEntity comment = CommentEntity.of(req.getContent(), board, user);
        return commentRepository.save(comment);
    }

    @Override
    @Transactional(readOnly = true)
    public CommentEntity getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "코런 댓글은 없어용"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommentEntity> getComments(Long boardId) {
        return commentRepository.findByBoard_IdOrderByCreatedAtAsc(boardId);
    }

    @Override
    public CommentEntity updateComment(Long id, CommentUpdateRequest req, Long userId) {

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "코런 댓글은 없어용"));

        // 작성자 검증
        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "당신 누끼야!");
        }

        comment.updateContent(req.getContent());
        return comment;
    }

    @Override
    public void deleteComment(Long id, Long userId) {
        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "코런 댓글은 없어용"));

        // 작성자 검증
        if (!comment.getUser().getId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "당신 누끼야!");
        }

        commentRepository.delete(comment);
    }
}
