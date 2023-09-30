package com.codesoom.assignment.user.application.service;

import com.codesoom.assignment.exception.UserNotFoundException;
import com.codesoom.assignment.user.application.port.out.GetUserPort;
import com.codesoom.assignment.user.application.port.out.SaveUserPort;
import com.codesoom.assignment.user.domain.User;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class UpdateUserServiceTest {

    private UpdateUserService updateUserService;
    private GetUserPort getUserPort;
    private SaveUserPort saveUserPort;
    private User user;
    private User source;

    @BeforeEach
    void setup(){
        saveUserPort = mock(SaveUserPort.class);
        getUserPort = mock(GetUserPort.class);
        updateUserService = new UpdateUserService(saveUserPort, getUserPort);
        user = new User(1L,"dh1234@gmail.com","dh","1111");
        source = new User(1L,"dh1234@gmail.com", "dhj","2222");

        given(getUserPort.findById(1L)).willReturn(user);
        given(getUserPort.findById(100L)).willThrow(UserNotFoundException.class);
        given(saveUserPort.save(user)).willReturn(source);
    }

    @Test
    void updateUserWithExistingId() {
        User updatedUser = updateUserService.updateUser(1L,source);

        verify(getUserPort).findById(1L);
        verify(saveUserPort).save(user);

        assertThat(updatedUser.getId()).isEqualTo(user.getId());
        assertThat(updatedUser.getEmail()).isEqualTo(user.getEmail());
        assertThat(updatedUser.getName()).isEqualTo(source.getName());
        assertThat(updatedUser.getPassword()).isEqualTo(source.getPassword());

    }

    @Test
    void updateUserWithNotExistingId(){
        assertThatThrownBy(()->updateUserService.updateUser(100L,source))
                .isInstanceOf(UserNotFoundException.class);

        verify(getUserPort).findById(100L);
    }
}
