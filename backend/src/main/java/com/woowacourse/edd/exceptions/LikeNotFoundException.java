package com.woowacourse.edd.exceptions;

import org.springframework.http.HttpStatus;

public class LikeNotFoundException extends ErrorResponseException {

    public static final String LIKE_NOT_FOUND_MESSAGE = "좋아요를 누르지 않았습니다.";

    public LikeNotFoundException() {
        super(LIKE_NOT_FOUND_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
