package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.CommentRequestDto;
import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.exceptions.InvalidContentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static com.woowacourse.edd.exceptions.InvalidContentsException.INVALID_CONTENTS_MESSAGE;
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
        saveComment(DEFAULT_VIDEO_ID, commentRequestDto)
            .expectStatus()
            .isCreated()
            .expectHeader()
            .exists("location")
            .expectBody()
            .jsonPath("$.id").isNotEmpty()
            .jsonPath("$.contents").isEqualTo(DEFAULT_CONTENTS)
            .jsonPath("$.author.id").isEqualTo(1L)
            .jsonPath("$.author.name").isEqualTo(DEFAULT_LOGIN_NAME)
            .jsonPath("$.createDate").isNotEmpty();
    }

    @Test
    void save_invalid_contents() {
        CommentRequestDto commentRequestDto = new CommentRequestDto(" ");
        assertFailBadRequest(saveComment(DEFAULT_VIDEO_ID, commentRequestDto).expectStatus(), INVALID_CONTENTS_MESSAGE);
    }

    @Test
    void retrieveComments() {
        getComments(DEFAULT_VIDEO_ID)
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$[0].id").isEqualTo(1L)
            .jsonPath("$[0].contents").isEqualTo("contents")
            .jsonPath("$[0].author.id").isEqualTo(1L)
            .jsonPath("$[0].author.name").isEqualTo(DEFAULT_LOGIN_NAME)
            .jsonPath("$[0].createDate").isNotEmpty();
    }

    @Test
    void retrieveComments_invalid_videoId() {
        getComments(100L)
            .expectStatus()
            .isOk()
            .expectBody()
            .jsonPath("$.length()").isEqualTo(0L);
    }

    private WebTestClient.ResponseSpec saveComment(Long videoId, CommentRequestDto commentRequestDto) {
        return executePost(VIDEO_URL+"/" + videoId + COMMENT_URL)
            .cookie(COOKIE_JSESSIONID,cookie)
            .body(Mono.just(commentRequestDto), CommentRequestDto.class)
            .exchange();
    }

    private WebTestClient.ResponseSpec getComments(Long videoId) {
        return executeGet(VIDEO_URL+"/"+videoId + COMMENT_URL)
            .exchange();
    }
}
