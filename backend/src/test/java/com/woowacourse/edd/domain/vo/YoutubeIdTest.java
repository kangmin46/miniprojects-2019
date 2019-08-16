package com.woowacourse.edd.domain.vo;

import com.woowacourse.edd.exceptions.InvalidYoutubeIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class YoutubeIdTest {

    @Test
    void valid_id() {
        assertDoesNotThrow(() -> new YoutubeId("abc"));
    }

    @Test
    void create_empty() {
        assertThrows(InvalidYoutubeIdException.class, () -> new YoutubeId(""));
    }

    @Test
    void create_null() {
        assertThrows(InvalidYoutubeIdException.class, () -> new YoutubeId(null));
    }

    @Test
    void create_blank() {
        assertThrows(InvalidYoutubeIdException.class, () -> new YoutubeId(" "));
    }
}