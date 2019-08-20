package com.woowacourse.edd.application.service;

import com.woowacourse.edd.application.converter.UserConverter;
import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.response.RedirectResponse;
import com.woowacourse.edd.domain.User;
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
        User user = userConverter.toEntity(userSaveRequestDto);
        return userConverter.toResponse(userRepository.save(user).getId());
    }
}
