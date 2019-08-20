package com.woowacourse.edd.application.converter;

import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.UserUpdateRequestDto;
import com.woowacourse.edd.application.response.RedirectResponse;
import com.woowacourse.edd.application.response.UserUpdateResponse;
import com.woowacourse.edd.domain.User;

public class UserConverter {
    private static final Boolean IS_DELETED = false;

    public User toSaveEntity(UserSaveRequestDto userSaveRequestDto) {
        return new User(userSaveRequestDto.getName(), userSaveRequestDto.getEmail(), userSaveRequestDto.getPassword(), IS_DELETED);
    }

    public User toUpdateEntity(UserUpdateRequestDto userUpdateRequestDto) {
        return new User(userUpdateRequestDto.getName(), userUpdateRequestDto.getEmail(), userUpdateRequestDto.getPassword(), IS_DELETED);
    }

    public RedirectResponse toSaveResponse(Long userId) {
        return new RedirectResponse("/", userId);
    }

    public UserUpdateResponse toUpdateResponse(User user) {
        return new UserUpdateResponse(user.getId(), user.getName(), user.getEmail(), user.getPassword());
    }
}
