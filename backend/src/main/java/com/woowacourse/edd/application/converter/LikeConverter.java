package com.woowacourse.edd.application.converter;

import com.woowacourse.edd.application.response.LikeCountResponse;
import com.woowacourse.edd.application.response.LikeResponse;
import com.woowacourse.edd.domain.Like;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.domain.Video;

import java.time.format.DateTimeFormatter;

public class LikeConverter {

    public static Like toEntity(Video video, User user) {
        return new Like(video ,user);
    }

    public static LikeResponse toResponse(Like like) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMddHH");
        String date = like.getCreateDate().format(format);
        return new LikeResponse(like.getVideo().getId(), like.getLikeUser().getId(), date);
    }

    public static LikeCountResponse toCountResponse(Long count) {
        return new LikeCountResponse(count);
    }
}
