package com.woowacourse.edd.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidEmailException extends ErrorResponseException {

    public static final String INVALID_EMAIL_MESSAGE = "이메일을 입력해주세요.";

    public InvalidEmailException() {
        super(INVALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
    }
}
