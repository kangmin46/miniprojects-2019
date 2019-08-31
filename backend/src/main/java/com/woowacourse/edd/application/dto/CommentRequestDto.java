package com.woowacourse.edd.application.dto;

import javax.validation.constraints.Size;

import static com.woowacourse.edd.application.dto.VideoSaveRequestDto.OVER_SIZE_CONTENTS_MESSAGE;

public class CommentRequestDto {

    @Size(max = 255, message = OVER_SIZE_CONTENTS_MESSAGE)
    private String contents;

    private CommentRequestDto() {
    }

    public CommentRequestDto(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }
}
