package com.codesoom.assignment.user.application.port.out;

import com.codesoom.assignment.user.domain.User;

public interface SaveUserPort {
    User save(User user);
}
