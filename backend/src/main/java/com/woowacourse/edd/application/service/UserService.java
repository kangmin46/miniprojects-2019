package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.converter.UserConverter;
import com.woowacourse.edd.application.converter.VideoConverter;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.UserUpdateRequestDto;
import com.woowacourse.edd.application.response.UserResponse;
import com.woowacourse.edd.application.response.VideoPreviewResponse;
import com.woowacourse.edd.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserInternalService userInternalService;
    private final VideoInternalService videoInternalService;

    @Autowired
    public UserService(UserInternalService userInternalService, VideoInternalService videoInternalService) {
        this.userInternalService = userInternalService;
        this.videoInternalService = videoInternalService;
    }

    public Long save(UserSaveRequestDto userSaveRequestDto) {
        User user = userInternalService.save(UserConverter.toSaveEntity(userSaveRequestDto));
        return user.getId();
    }

    public UserResponse findById(Long id) {
        User user = userInternalService.findById(id);
        return UserConverter.toResponse(user);
    }

    public UserResponse update(Long id, Long loggedInId, UserUpdateRequestDto userUpdateRequestDto) {
        User user = userInternalService.update(id, loggedInId, UserConverter.escapeUpdateRequestDto(userUpdateRequestDto));
        return UserConverter.toResponse(user);
    }

    public void delete(Long id, Long loggedInId) {
        userInternalService.delete(id, loggedInId);
    }

    public List<VideoPreviewResponse> retrieveVideos(Long userId) {
        return videoInternalService.findByCreatorId(userId).stream()
            .map(VideoConverter::toPreviewResponse)
            .collect(Collectors.toList());
    }
}

