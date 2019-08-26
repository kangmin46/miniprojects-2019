package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.CommentRequestDto;
import com.woowacourse.edd.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.woowacourse.edd.presentation.controller.CommentController.COMMENT_URL;
import static com.woowacourse.edd.presentation.controller.VideoController.VIDEO_URL;

public class CommentControllerTests extends BasicControllerTests {

    private static final String DEFAULT_CONTENTS = "contents";
    private String cookie;

    @BeforeEach
    void setUp() {
        LoginRequestDto loginRequestDto = new LoginRequestDto(DEFAULT_LOGIN_EMAIL, DEFAULT_LOGIN_PASSWORD);
        cookie = getLoginCookie(loginRequestDto);
    }

    @Test
    void save() {
        CommentRequestDto commentRequestDto = new CommentRequestDto(DEFAULT_CONTENTS);
        saveComment(commentRequestDto)
            .expectStatus()
            .isCreated()
            .expectHeader()
            .valueMatches("location", VIDEO_URL+"/" + DEFAULT_VIDEO_ID + COMMENT_URL + "/1")
            .expectBody()
            .jsonPath("$.id").isEqualTo(1L)
            .jsonPath("$.contents").isEqualTo(DEFAULT_CONTENTS)
            .jsonPath("$.author.id").isEqualTo(1L)
            .jsonPath("$.author.name").isEqualTo(DEFAULT_LOGIN_NAME)
            .jsonPath("$.createDate").isNotEmpty();
    }

    private WebTestClient.ResponseSpec saveComment(CommentRequestDto commentRequestDto) {
        return executePost(VIDEO_URL+"/" + DEFAULT_VIDEO_ID + COMMENT_URL)
            .cookie(COOKIE_JSESSIONID,cookie)
            .body(Mono.just(commentRequestDto), CommentRequestDto.class)
            .exchange();
    }
}
