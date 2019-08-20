package com.woowacourse.edd.application.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class UserSaveRequestDto {
    @Pattern(regexp = "^([A-Za-z가-힣]{2,16})$", message = "이름은 2자이상 16자이하의 영문이나 숫자여야 합니다.")
    private String name;
    @Email
    private String email;
    @Pattern(regexp = "[a-zA-Z0-9!@#$%^&*(),.?\\\":{}|<>]{8,}", message = "안됨")
    private String password;

    private UserSaveRequestDto() {
    }

    public UserSaveRequestDto(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
