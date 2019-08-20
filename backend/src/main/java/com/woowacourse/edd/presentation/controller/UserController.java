package com.woowacourse.edd.presentation.controller;

import com.woowacourse.edd.application.dto.UserSaveRequestDto;
import com.woowacourse.edd.application.dto.UserUpdateRequestDto;
import com.woowacourse.edd.application.response.RedirectResponse;
import com.woowacourse.edd.application.response.UserUpdateResponse;
import com.woowacourse.edd.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<RedirectResponse> saveUser(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        return ResponseEntity.ok(userService.save(userSaveRequestDto));
    }

    @PutMapping
    public ResponseEntity<UserUpdateResponse> updateUser(@Valid @RequestBody UserUpdateRequestDto userUpdateRequestDto) {
        return ResponseEntity.ok(userService.update(userUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
