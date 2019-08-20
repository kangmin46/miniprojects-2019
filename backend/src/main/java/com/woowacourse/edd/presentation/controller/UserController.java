package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.response.RedirectResponse;
import com.woowacourse.edd.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<RedirectResponse> saveUser(@Valid  @RequestBody UserSaveRequestDto userSaveRequestDto) {
        return ResponseEntity.ok(userService.save(userSaveRequestDto));
    }

}
