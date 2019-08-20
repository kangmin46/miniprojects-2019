package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.converter.UserConverter;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.UserUpdateRequestDto;
import com.woowacourse.edd.application.response.RedirectResponse;
import com.woowacourse.edd.application.response.UserUpdateResponse;
import com.woowacourse.edd.domain.User;
import com.woowacourse.edd.exceptions.UserNotFoundException;
import com.woowacourse.edd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter = new UserConverter();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public RedirectResponse save(UserSaveRequestDto userSaveRequestDto) {
        User user = userConverter.toSaveEntity(userSaveRequestDto);
        return userConverter.toSaveResponse(userRepository.save(user).getId());
    }

    public UserUpdateResponse update(UserUpdateRequestDto userUpdateRequestDto) {
        User user = userRepository.findById(userUpdateRequestDto.getId()).orElseThrow(UserNotFoundException::new);
        user.update(userUpdateRequestDto);
        return userConverter.toUpdateResponse(user);

    }

    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        user.delete();
    }
}
