package com.codesoom.assignment.user.application.service;

import com.codesoom.assignment.user.application.port.in.DeleteUserUseCase;
import com.codesoom.assignment.user.application.port.out.DeleteUserPort;
import com.codesoom.assignment.user.application.port.out.GetUserPort;
import com.codesoom.assignment.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {
    private final DeleteUserPort deleteUserPort;
    private final GetUserPort getUserPort;

    @Override
    public void deleteUser(Long id) {
        User user = getUserPort.findById(id);
        deleteUserPort.delete(user);
    }
}
