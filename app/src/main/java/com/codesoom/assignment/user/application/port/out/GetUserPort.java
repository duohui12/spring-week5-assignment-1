package com.codesoom.assignment.user.application.port.out;

import com.codesoom.assignment.user.domain.User;

public interface GetUserPort {
    User findById(Long id);
}
