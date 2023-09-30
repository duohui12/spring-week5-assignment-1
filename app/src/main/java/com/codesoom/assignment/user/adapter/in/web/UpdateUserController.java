package com.codesoom.assignment.user.adapter.in.web;

import com.codesoom.assignment.user.application.port.in.UpdateUserUseCase;
import com.codesoom.assignment.user.domain.User;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
class UpdateUserController {

    private final UpdateUserUseCase updateUserUseCase;
    private final Mapper mapper;

    @PatchMapping("{id}")
    UpdateUserResponse update(
            @PathVariable Long id,
            @RequestBody @Valid UpdateUserRequest updateUserRequest) {
        User user = updateUserUseCase.updateUser(id, mapper.map(updateUserRequest, User.class));
        return mapper.map(user, UpdateUserResponse.class);
    }

}

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UpdateUserRequest {
    @NotBlank
    @Mapping("name")
    private String name;

    @NotBlank
    @Mapping("password")
    private String password;
}

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UpdateUserResponse {
    @NotNull
    @Mapping("id")
    private Long id;

    @NotBlank
    @Mapping("name")
    private String name;

    @NotBlank
    @Mapping("email")
    private String email;
}
