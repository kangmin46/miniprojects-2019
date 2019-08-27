package com.woowacourse.edd.application.service;

import com.woowacourse.edd.domain.Comment;
import com.woowacourse.edd.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentInternalService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentInternalService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    public List<Comment> retrieve(Long videoId) {
        return commentRepository.findCommentsByVideo_Id(videoId);
    }
}
