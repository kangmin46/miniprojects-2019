package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.converter.CommentConverter;
import com.woowacourse.edd.application.dto.CommentRequestDto;
import com.woowacourse.edd.application.response.CommentResponse;
import com.woowacourse.edd.domain.Comment;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CommentService {

    private VideoInternalService videoInternalService;
    private CommentInternalService commentInternalService;
    private UserInternalService userInternalService;

    @Autowired
    public CommentService(VideoInternalService videoInternalService, CommentInternalService commentInternalService, UserInternalService userInternalService) {
        this.videoInternalService = videoInternalService;
        this.commentInternalService = commentInternalService;
        this.userInternalService = userInternalService;
    }

    public CommentResponse save(Long videoId, Long authorId, CommentRequestDto commentRequestDto) {
        Video video = videoInternalService.findById(videoId);
        User author = userInternalService.findById(authorId);

        Comment comment = CommentConverter.toEntity(video, author, commentRequestDto);
        comment = commentInternalService.save(comment);
        return CommentConverter.toResponse(comment);
    }
}
