package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.response.LikeCountResponse;
import com.woowacourse.edd.application.response.SessionUser;
import com.woowacourse.edd.application.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.net.URI;

@RestController
public class LikeController {
    private final LikeService likeService;

    @Autowired
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/v1/videos/{videoId}/likes")
    public ResponseEntity save(@PathVariable Long videoId, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        likeService.save(videoId, sessionUser.getId());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/v1/videos/{videoId}/likes")
    public ResponseEntity delete(@PathVariable Long videoId, HttpSession session) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("user");
        likeService.delete(videoId, sessionUser.getId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("v1/videos/{videoId}/likes")
    public ResponseEntity countLikes(@PathVariable Long videoId) {
        LikeCountResponse likeCountResponse = likeService.retrieveCount(videoId);
        return ResponseEntity.ok(likeCountResponse);
    }
}
