package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.VideoSaveRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.woowacourse.edd.exceptions.LikeNotFoundException.LIKE_NOT_FOUND_MESSAGE;
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
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto("newUser", "newUser@naver.com", "p@ssW0rd","p@ssW0rd");
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
            .isOk()
            .expectBody()
            .jsonPath("$.like").isEqualTo(false);
    }

    @Test
    @DisplayName("좋아요를 누르지 않은 상태에서 좋아요 취소 요청을 할 경우")
    void invaid_delete() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto("newUser", "newUser2@naver.com", "p@ssW0rd","p@ssW0rd");
        signUp(userSaveRequestDto);
        LoginRequestDto loginRequestDto = new LoginRequestDto("newUser2@naver.com", "p@ssW0rd");
        String cookie = getLoginCookie(loginRequestDto);

        VideoSaveRequestDto videoSaveRequestDto = new VideoSaveRequestDto("abcdee", "title2", "contents2");

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

        assertFailBadRequest(deleteLike(userId, cookie), LIKE_NOT_FOUND_MESSAGE);
    }

    @Test
    void duplicate_like() {
        saveLike(1L)
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.like").isEqualTo(true);
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
        deleteLike(1L, cookie)
            .expectStatus()
            .isOk();
    }

    private WebTestClient.ResponseSpec saveLike(Long userId) {
        return executePost("v1/videos/" + userId + "/likes")
            .cookie(COOKIE_JSESSIONID, cookie)
            .exchange();
    }


    private WebTestClient.ResponseSpec deleteLike(Long userId, String cookie) {
        return executeDelete("v1/videos/"+ userId+ "/likes")
            .cookie(COOKIE_JSESSIONID, cookie)
            .exchange();
    }
}
