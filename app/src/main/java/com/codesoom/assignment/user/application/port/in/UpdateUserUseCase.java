package com.codesoom.assignment.user.application.port.in;

import com.codesoom.assignment.user.domain.User;

public interface UpdateUserUseCase {
    User updateUser(Long id, User user);
}
