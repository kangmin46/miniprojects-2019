package com.woowacourse.edd.domain;

import com.woowacourse.edd.exceptions.InvalidContentsException;
import com.woowacourse.edd.exceptions.InvalidTitleException;
import com.woowacourse.edd.exceptions.InvalidYoutubeIdException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class VideoTest {

    private static final String VALID_YOUTUBE_ID = "abc";
    private static final String VALID_TITLE = "abcd";
    private static final String VALID_CONTENTS = "abcde";
    private static final String EMPTY = "";
    private static final String BLANK = " ";

    @Test
    void create_video() {
        assertDoesNotThrow(() -> new Video(VALID_YOUTUBE_ID, VALID_TITLE, VALID_CONTENTS));
    }

    @Test
    void check_youtubeId_empty() {
        assertThrows(InvalidYoutubeIdException.class, () -> new Video(EMPTY, VALID_TITLE, VALID_CONTENTS));
    }

    @Test
    void check_youtubeId__null() {
        assertThrows(InvalidYoutubeIdException.class, () -> new Video(null, VALID_TITLE, VALID_CONTENTS));
    }

    @Test
    void check_youtubeId__blank() {
        assertThrows(InvalidYoutubeIdException.class, () -> new Video(" ", VALID_TITLE, VALID_CONTENTS));
    }

    @Test
    void check_title_empty() {
        assertThrows(InvalidTitleException.class, () -> new Video(VALID_YOUTUBE_ID, EMPTY, VALID_CONTENTS));
    }

    @Test
    void check_title_null() {
        assertThrows(InvalidTitleException.class, () -> new Video(VALID_YOUTUBE_ID, null, VALID_CONTENTS));
    }

    @Test
    void check_title_blank() {
        assertThrows(InvalidTitleException.class, () -> new Video(VALID_YOUTUBE_ID, " ", VALID_CONTENTS));
    }

    @Test
    void check_contents_empty() {
        assertThrows(InvalidContentsException.class, () -> new Video(VALID_YOUTUBE_ID, VALID_TITLE, EMPTY));
    }

    @Test
    void check_contents_null() {
        assertThrows(InvalidContentsException.class, () -> new Video(VALID_YOUTUBE_ID, VALID_TITLE, null));
    }

    @Test
    void check_contents_blank() {
        assertThrows(InvalidContentsException.class, () -> new Video(VALID_YOUTUBE_ID, VALID_TITLE, " "));
    }


}