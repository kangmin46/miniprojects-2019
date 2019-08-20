package com.woowacourse.edd.domain;

import com.woowacourse.edd.application.dto.UserUpdateRequestDto;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "user_name")
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createDate;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateDate;

    private Boolean isDeleted;

    private User() {

    }

    public User(String name, String email, String password, Boolean isDeleted) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void update(UserUpdateRequestDto userUpdateRequestDto) {
        this.name = userUpdateRequestDto.getName();
        this.email = userUpdateRequestDto.getEmail();
        this.password = userUpdateRequestDto.getPassword();
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

    public void delete() {
        this.isDeleted = true;
    }
}
