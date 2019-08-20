package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.EddApplicationTests;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.UserUpdateRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class UserControllerTests extends EddApplicationTests {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void user_save() {
        UserSaveRequestDto userSaveRequestDto = new UserSaveRequestDto("robby", "shit@email.com", "P@ssW0rd");
        webTestClient.post()
                .uri("/v1/users")
                .body(Mono.just(userSaveRequestDto), UserSaveRequestDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.redirectUrl").isEqualTo("/")
                .jsonPath("$.userId").isEqualTo(2L);
    }

    @Test
    void user_update() {
        UserUpdateRequestDto userUpdateRequestDto = new UserUpdateRequestDto(1L, "jm", "hansome@gmail.com", "P!ssW0rd");

        webTestClient.put()
                .uri("/v1/users")
                .body(Mono.just(userUpdateRequestDto), UserUpdateRequestDto.class)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(userUpdateRequestDto.getId())
                .jsonPath("$.name").isEqualTo(userUpdateRequestDto.getName())
                .jsonPath("$.email").isEqualTo(userUpdateRequestDto.getEmail())
                .jsonPath("$.password").isEqualTo(userUpdateRequestDto.getPassword());
    }

    @Test
    void user_delete_not_found() {
        webTestClient.delete()
                .uri("/v1/users/999")
                .exchange()
                .expectStatus()
                .isNotFound();  //404
    }

    @Test
    void user_delete_no_content() {
        webTestClient.delete()
                .uri("/v1/users/1")
                .exchange()
                .expectStatus()
                .isNoContent();
    }


}
