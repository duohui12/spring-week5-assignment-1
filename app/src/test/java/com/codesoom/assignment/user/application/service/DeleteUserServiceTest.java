package com.codesoom.assignment.user.application.service;

import com.codesoom.assignment.exception.UserNotFoundException;
import com.codesoom.assignment.product.ProductFixture;
import com.codesoom.assignment.user.application.port.out.DeleteUserPort;
import com.codesoom.assignment.user.application.port.out.GetUserPort;
import com.codesoom.assignment.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteUserServiceTest {

    private DeleteUserService deleteUserService;
    private DeleteUserPort deleteUserPort;
    private GetUserPort getUserPort;
    private User user;

    @BeforeEach
    void setup(){
        deleteUserPort = mock(DeleteUserPort.class);
        getUserPort = mock(GetUserPort.class);
        deleteUserService = new DeleteUserService(deleteUserPort,getUserPort);
        user = new User(1L,"dh1234@gmail.com","dh","1111");

        given(getUserPort.findById(1L)).willReturn(user);
        given(getUserPort.findById(100L)).willThrow(UserNotFoundException.class);
    }

    @Test
    void deleteUserWithExistingId() {
        deleteUserService.deleteUser(1L);

        verify(getUserPort).findById(1L);
        verify(deleteUserPort).delete(user);
    }

    @Test
    void deleteUserWithNotExistringId() {
        assertThatThrownBy(()->deleteUserService.deleteUser(100L)).isInstanceOf(UserNotFoundException.class);

        verify(getUserPort).findById(100L);
    }
}
