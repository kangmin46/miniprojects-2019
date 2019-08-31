package com.woowacourse.edd.application.dto;

import javax.validation.constraints.Size;

public class VideoUpdateRequestDto {

    @Size(max = 255, message = "너무 긴 유튜브 아이디는 안됩니다.")
    private String youtubeId;
    @Size(max = 80, message = "80자 이상의 제목은 안됩니다.")
    private String title;
    @Size(max = 255, message =  "255자 이상의 글자는 안됩니다.")
    private String contents;

    private VideoUpdateRequestDto() {
    }

    public VideoUpdateRequestDto(String youtubeId, String title, String contents) {
        this.youtubeId = youtubeId;
        this.title = title;
        this.contents = contents;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
