package com.codesoom.assignment.user.application.port.in;

import com.codesoom.assignment.user.domain.User;

public interface DeleteUserUseCase {
    void deleteUser(Long id);
}
