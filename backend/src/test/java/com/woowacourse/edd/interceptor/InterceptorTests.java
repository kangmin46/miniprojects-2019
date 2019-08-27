package com.woowacourse.edd.interceptor;

import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.presentation.controller.BasicControllerTests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.StatusAssertions;
import reactor.core.publisher.Mono;

import static com.woowacourse.edd.exceptions.InvalidAccessException.INVALID_ACCESS_MESSAGE;
import static com.woowacourse.edd.presentation.controller.LoginController.LOGIN_URL;
import static com.woowacourse.edd.presentation.controller.UserController.USER_URL;

public class InterceptorTests extends BasicControllerTests {

    private String sessionId;

    @BeforeEach
    void setUp() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("kangmin789@naver.com", "P@ssW0rd");

        sessionId = getLoginCookie(loginRequestDto);
    }

    @Test
    void post_user() {
        UserSaveRequestDto signUpUserDto = new UserSaveRequestDto("conas91", "conas91@gmail.com", "p@ssW0rd");
        StatusAssertions statusAssertions = executePost(USER_URL).cookie(COOKIE_JSESSIONID, sessionId)
            .body(Mono.just(signUpUserDto), UserSaveRequestDto.class)
            .exchange()
            .expectStatus();
        assertFailBadRequest(statusAssertions, INVALID_ACCESS_MESSAGE);
    }

    @Test
    void post_login() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("kangmin789@naver.com", "P@ssW0rd");
        StatusAssertions statusAssertions = executePost(LOGIN_URL).cookie(COOKIE_JSESSIONID, sessionId)
            .body(Mono.just(loginRequestDto), LoginRequestDto.class)
            .exchange()
            .expectStatus();
        assertFailBadRequest(statusAssertions, INVALID_ACCESS_MESSAGE);
    }
}
