package com.woowacourse.edd.domain.vo;

import com.woowacourse.edd.exceptions.InvalidTitleException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TitleTest {
    @Test
    void check_empty() {
        assertThrows(InvalidTitleException.class, () -> new Title(""));
    }

    @Test
    void check_null() {
        assertThrows(InvalidTitleException.class, () -> new Title(null));
    }

    @Test
    void check_blank() {
        assertThrows(InvalidTitleException.class, () -> new Title(" "));
    }

    @Test
    void valid_title() {
        assertDoesNotThrow(() -> new Title("abcd"));
    }
}