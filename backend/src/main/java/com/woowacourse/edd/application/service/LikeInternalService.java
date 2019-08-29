package com.woowacourse.edd.application.service;

import com.woowacourse.edd.domain.Like;
import com.woowacourse.edd.domain.Video;
import com.woowacourse.edd.exceptions.LikeNotFoundException;
import com.woowacourse.edd.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LikeInternalService {
    private final LikeRepository likeRepository;

    @Autowired
    public LikeInternalService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void save(Like like) {
        likeRepository.save(like);
    }

    @Transactional(readOnly = true)
    public boolean isLikeExist(Long videoId, Long userId) {
        return likeRepository.findByVideo_IdAndLikeUser_Id(videoId, userId).isPresent();
    }

    public void delete(Long videoId, Long userId) {
        Like like = findByVideoIdAndUserId(videoId, userId);
        likeRepository.delete(like);
    }

    @Transactional(readOnly = true)
    public Like findByVideoIdAndUserId(Long videoId, Long userId) {
        return likeRepository.findByVideo_IdAndLikeUser_Id(videoId, userId).orElseThrow(LikeNotFoundException::new);
    }

    public Long retrieve(Long videoId) {
        return likeRepository.countByVideo_Id(videoId);
    }
}
