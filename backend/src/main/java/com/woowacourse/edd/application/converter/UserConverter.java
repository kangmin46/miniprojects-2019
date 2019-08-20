package com.woowacourse.edd.application.converter;

import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.VideoSaveRequestDto;
import com.woowacourse.edd.application.response.RedirectResponse;
import com.woowacourse.edd.application.response.VideoResponse;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.domain.Video;

import java.time.format.DateTimeFormatter;

public class UserConverter {
    public User toEntity(UserSaveRequestDto userSaveRequestDto) {
        return new User(userSaveRequestDto.getName(), userSaveRequestDto.getEmail(), userSaveRequestDto.getPassword());
    }

    public RedirectResponse toResponse(Long userId) {
        return new RedirectResponse("/", userId);
    }
}
