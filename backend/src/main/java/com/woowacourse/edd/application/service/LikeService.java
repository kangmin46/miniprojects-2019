package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.converter.LikeConverter;
import com.woowacourse.edd.application.response.LikeCountResponse;
import com.woowacourse.edd.application.response.LikeResponse;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeService {

    private final LikeInternalService likeInternalService;
    private final VideoInternalService videoInternalService;
    private final UserInternalService userInternalService;

    @Autowired
    public LikeService(LikeInternalService internalLikeService, VideoInternalService videoInternalService, UserInternalService userInternalService) {
        this.likeInternalService = internalLikeService;
        this.videoInternalService = videoInternalService;
        this.userInternalService = userInternalService;
    }

    public LikeResponse save(Long videoId, Long userId) {
        Video video = videoInternalService.findById(videoId);
        User user = userInternalService.findById(userId);
        if (likeInternalService.isLikeDoesNotExist(videoId, userId)) {
            likeInternalService.save(LikeConverter.toEntity(video, user));
            return LikeConverter.toResponse(false);
        }
        return LikeConverter.toResponse(true);
    }

    public LikeCountResponse retrieveCount(Long videoId) {
        videoInternalService.findById(videoId);
        Long count = likeInternalService.retrieve(videoId);
        return LikeConverter.toCountResponse(count);
    }

    public void delete(Long videoId, Long userId) {
        likeInternalService.delete(videoId, userId);
    }
}
