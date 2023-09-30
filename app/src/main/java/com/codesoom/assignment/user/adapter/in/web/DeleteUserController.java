package com.codesoom.assignment.user.adapter.in.web;

import com.codesoom.assignment.user.application.port.in.DeleteUserUseCase;
import com.codesoom.assignment.user.application.port.out.DeleteUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
class DeleteUserController {

    private final DeleteUserUseCase deleteUserUseCase;

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Long id) {
        deleteUserUseCase.deleteUser(id);
    }

}
