package com.codesoom.assignment.user.application.service;

import com.codesoom.assignment.user.application.port.in.RegisterUserUseCase;
import com.codesoom.assignment.user.application.port.out.SaveUserPort;
import com.codesoom.assignment.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class RegisterUserService implements RegisterUserUseCase {
    private final SaveUserPort saveUserPort;

    @Override
    public User registerUser(User user) {
        return saveUserPort.save(user);
    }
}
