package com.woowacourse.edd.application.response;

import com.woowacourse.edd.domain.User;

public class LikeResponse {

    private long videoId;
    private long userId;
    private String createDate;

    public LikeResponse(long videoId, long userId, String createDate) {
        this.videoId = videoId;
        this.userId = userId;
        this.createDate = createDate;
    }

    public long getVideoId() {
        return videoId;
    }

    public long getUserId() {
        return userId;
    }

    public String getCreateDate() {
        return createDate;
    }
}
