package com.codesoom.assignment.user.application.service;

import com.codesoom.assignment.user.application.port.out.SaveUserPort;
import com.codesoom.assignment.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RegisterUserServiceTest {

    private RegisterUserService registerUserService;
    private SaveUserPort saveUserPort;
    private User user;

    @BeforeEach
    void setup(){
        saveUserPort = mock(SaveUserPort.class);
        registerUserService = new RegisterUserService(saveUserPort);
        user = new User(1L,"dh1234@gmail.com","dh","1111");

        given(saveUserPort.save(any(User.class))).willReturn(user);
    }

    @Test
    void registerUser() {
        User createdUser = registerUserService.registerUser(user);

        assertThat(createdUser.getId()).isEqualTo(1L);
        assertThat(createdUser.getEmail()).isEqualTo("dh1234@gmail.com");
        assertThat(createdUser.getName()).isEqualTo("dh");
        assertThat(createdUser.getPassword()).isEqualTo("1111");
    }
}
