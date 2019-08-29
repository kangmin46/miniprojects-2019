package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.VideoSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.woowacourse.edd.presentation.controller.VideoController.VIDEO_URL;

public class LikeControllerTests extends BasicControllerTests {
    private String cookie;

    @BeforeEach
    void setUp() {
        LoginRequestDto loginRequestDto = new LoginRequestDto(DEFAULT_LOGIN_EMAIL, DEFAULT_LOGIN_PASSWORD);
        cookie = getLoginCookie(loginRequestDto);
    }

    @Test
    void save() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto("newUser", "newUser@naver.com", "p@ssW0rd");
        signUp(userSaveRequestDto);
        LoginRequestDto loginRequestDto = new LoginRequestDto("newUser@naver.com", "p@ssW0rd");
        String cookie = getLoginCookie(loginRequestDto);

        VideoSaveRequestDto videoSaveRequestDto = new VideoSaveRequestDto("abc", "title", "contents");

        String returnUrl = executePost(VIDEO_URL)
            .cookie(COOKIE_JSESSIONID, cookie)
            .body(Mono.just(videoSaveRequestDto), VideoSaveRequestDto.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody()
            .returnResult()
            .getResponseHeaders()
            .getLocation()
            .toASCIIString();

        String[] urls = returnUrl.split("/");
        Long userId = Long.valueOf(urls[urls.length - 1]);

        saveLike(userId)
            .expectStatus()
            .isOk();
    }

    @Test
    void duplicate_like() {
        saveLike(1L)
            .expectStatus()
            .isBadRequest();
    }

    @Test
    void read() {
        executeGet("v1/videos/1/likes")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.count").isEqualTo(1L);
    }

    @Test
    void disLike() {
        LoginRequestDto loginRequestDto = new LoginRequestDto(DEFAULT_LOGIN_EMAIL, DEFAULT_LOGIN_PASSWORD);
        String cookie = getLoginCookie(loginRequestDto);
        executeDelete("v1/videos/1/likes")
            .cookie(COOKIE_JSESSIONID, cookie)
            .exchange()
            .expectStatus()
            .isOk();
    }

    private WebTestClient.ResponseSpec saveLike(Long userId) {
        return executePost("v1/videos/+" + userId + "/likes")
            .cookie(COOKIE_JSESSIONID, cookie)
            .exchange();
    }
}
