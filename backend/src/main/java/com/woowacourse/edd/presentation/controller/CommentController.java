package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.CommentRequestDto;
import com.woowacourse.edd.application.response.CommentResponse;
import com.woowacourse.edd.application.response.SessionUser;
import com.woowacourse.edd.application.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.util.List;

import static com.woowacourse.edd.presentation.controller.VideoController.VIDEO_URL;

@RestController
public class CommentController {

    static final String COMMENT_URL = "/comments";
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping(VIDEO_URL+"/{videoId}"+COMMENT_URL)
    public ResponseEntity save(@PathVariable Long videoId, @RequestBody CommentRequestDto commentRequestDto, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        CommentResponse commentResponse = commentService.save(videoId, sessionUser.getId(), commentRequestDto);
        return ResponseEntity.created(URI.create(VIDEO_URL+"/"+videoId+COMMENT_URL+"/"+commentResponse.getId())).body(commentResponse);
    }

    @GetMapping(VIDEO_URL+"/{videoId}" + COMMENT_URL)
    public ResponseEntity<List<CommentResponse>> retrieve(@PathVariable Long videoId) {
        List<CommentResponse> commentResponses = commentService.retrieve(videoId);
        return ResponseEntity.ok(commentResponses);
    }
}
