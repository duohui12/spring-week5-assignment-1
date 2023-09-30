package com.codesoom.assignment.user.adapter.in.web;

import com.codesoom.assignment.user.application.port.in.RegisterUserUseCase;
import com.codesoom.assignment.user.domain.User;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.Mapping;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin
class RegisterUserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final Mapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    RegisterUserResponse create(@RequestBody @Valid RegisterUserRequest registerUserRequest)
    {
        User user = registerUserUseCase.registerUser(mapper.map(registerUserRequest,User.class));
        return mapper.map(user,RegisterUserResponse.class);
    }

}


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class RegisterUserRequest {
    @NotBlank
    @Mapping("name")
    private String name;

    @NotBlank
    @Mapping("email")
    private String email;

    @NotBlank
    @Mapping("password")
    private String password;
}

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class RegisterUserResponse {
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
