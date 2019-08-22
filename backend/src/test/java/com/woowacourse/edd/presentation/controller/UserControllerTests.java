package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.LoginRequestDto;
import com.woowacourse.edd.application.dto.UserRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.StatusAssertions;
import reactor.core.publisher.Mono;

import static com.woowacourse.edd.presentation.controller.UserController.USER_URL;

public class UserControllerTests extends BasicControllerTests {
    private String sid;

    @BeforeEach
    void setUp() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("kangmin789@naver.com", "P@ssW0rd");

        sid = getLoginCookie(loginRequestDto);
    }

    @Test
    void user_save() {
        UserRequestDto userSaveRequestDto = new UserRequestDto("robby", "shit@email.com", "P@ssW0rd");

        findUser(getUrl(signUp(userSaveRequestDto)))
            .isOk()
            .expectBody()
            .jsonPath("$.name").isEqualTo("robby")
            .jsonPath("$.email").isEqualTo("shit@email.com");
    }

    @Test
    void user_update() {
        UserRequestDto userSaveRequestDto = new UserRequestDto("robby", "shit123@email.com", "P@ssW0rd");
        UserRequestDto userRequestDto = new UserRequestDto("jm", "hansome@gmail.com", "P!ssW0rd");

        updateUser(userRequestDto, getUrl(signUp(userSaveRequestDto)))
            .isOk()
            .expectBody()
            .jsonPath("$.name").isEqualTo(userRequestDto.getName())
            .jsonPath("$.email").isEqualTo(userRequestDto.getEmail());
    }

    @Test
    void user_delete_not_found() {
        deleteUser(USER_URL + "/999")
            .isNotFound();  //404
    }

    @Test
    void user_delete_no_content() {
        UserRequestDto userSaveRequestDto = new UserRequestDto("robby", "shit222@email.com", "P@ssW0rd");

        deleteUser(getUrl(signUp(userSaveRequestDto)))
            .isNoContent();
    }

    @Test
    void find_by_id() {
        findUser(USER_URL + "/1").isOk();
    }

    private String getUrl(EntityExchangeResult<byte[]> entityExchangeResult) {
        return entityExchangeResult
            .getResponseHeaders()
            .getLocation()
            .toASCIIString();
    }

    private StatusAssertions findUser(String url) {
        return webTestClient
            .get()
            .uri(url)
            .exchange()
            .expectStatus();
    }

    private StatusAssertions updateUser(UserRequestDto userRequestDto, String uri) {
        return webTestClient.put()
            .uri(uri)
            .cookie(COOKIE_JSESSIONID, sid)
            .body(Mono.just(userRequestDto), UserRequestDto.class)
            .exchange()
            .expectStatus();
    }

    private StatusAssertions deleteUser(String url) {
        return webTestClient.delete()
            .uri(url)
            .cookie(COOKIE_JSESSIONID, sid)
            .exchange()
            .expectStatus();
    }
}
