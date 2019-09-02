package com.woowacourse.edd.application.response;

import com.woowacourse.edd.domain.User;

public class LikeResponse {

    private boolean isLike;

    public LikeResponse(boolean isLike) {
        this.isLike = isLike;
    }

    public boolean isLike() {
        return isLike;
    }
}
