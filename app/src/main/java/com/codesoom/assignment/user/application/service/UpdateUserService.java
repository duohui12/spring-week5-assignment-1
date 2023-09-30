package com.codesoom.assignment.user.application.service;

import com.codesoom.assignment.user.application.port.in.UpdateUserUseCase;
import com.codesoom.assignment.user.application.port.out.GetUserPort;
import com.codesoom.assignment.user.application.port.out.SaveUserPort;
import com.codesoom.assignment.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
class UpdateUserService implements UpdateUserUseCase {
    private final SaveUserPort saveUserPort;
    private final GetUserPort getUserPort;

    @Override
    public User updateUser(Long id, User source) {
        User user = getUserPort.findById(id);
        user.change(source.getName(), source.getPassword());

        return saveUserPort.save(user);
    }
}
