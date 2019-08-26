package com.woowacourse.edd.domain;

import com.woowacourse.edd.exceptions.InvalidContentsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentTests extends DomainBasicTests {

    private Video video;
    private User author;

    @BeforeEach
    void setUp() {
        author = new User("robby", "robby@robby.com", "P@ssw0rd");
        video = new Video("youtubeId##", "title", "contents", author);
    }

    @Test
    void create() {
        assertDoesNotThrow(() -> {
            new Comment("contents", video, author);
        });
    }

    @ParameterizedTest
    @MethodSource("invalidStrings")
    void contents_invalid(final String invalidString) {
        assertThrows(InvalidContentsException.class, () -> new Comment(invalidString, video, author));
    }

}
