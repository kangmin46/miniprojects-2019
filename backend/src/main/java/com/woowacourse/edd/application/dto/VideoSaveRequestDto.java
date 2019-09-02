package com.woowacourse.edd.application.dto;

import javax.validation.constraints.Size;

public class VideoSaveRequestDto {

    public static final String OVER_SIZE_YOUTUBEID_MESSAGE =  "너무 긴 유튜브 아이디는 안됩니다.";
    public static final String OVER_SIZE_TITLE_MESSAGE =  "80자 이상의 제목은 안됩니다.";
    public static final String OVER_SIZE_CONTENTS_MESSAGE =  "255자 이상의 글자는 안됩니다.";

    @Size(max = 255, message = OVER_SIZE_YOUTUBEID_MESSAGE)
    private String youtubeId;
    @Size(max = 80, message = OVER_SIZE_TITLE_MESSAGE)
    private String title;
    @Size(max = 255, message = OVER_SIZE_CONTENTS_MESSAGE)
    private String contents;

    private VideoSaveRequestDto() {
    }

    public VideoSaveRequestDto(String youtubeId, String title, String contents) {
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
