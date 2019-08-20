package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.EddApplicationTests;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.VideoSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class UserControllerTests extends EddApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void user_save() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto("robby", "shit@email.com", "Password123!");
        webTestClient.post()
                .uri("/v1/users")
                .body(Mono.just(userSaveRequestDto), UserSaveRequestDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.redirectUrl").isEqualTo("/")
                .jsonPath("$.userId").isEqualTo(1L);
    }
}
