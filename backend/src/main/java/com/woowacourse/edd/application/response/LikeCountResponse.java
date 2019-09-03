package com.woowacourse.edd.application.response;

public class LikeCountResponse {

    private long count;

    public LikeCountResponse(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }
}
