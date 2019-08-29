package com.woowacourse.edd.exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateLikeException extends ErrorResponseException {

    public static final String DUPLICATE_LIKE_MESSAGE = "이미 좋아요 한 동영상 입니다.";

    public DuplicateLikeException() {
        super(DUPLICATE_LIKE_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
