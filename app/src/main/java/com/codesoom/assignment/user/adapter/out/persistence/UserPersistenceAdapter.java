package com.codesoom.assignment.user.adapter.out.persistence;


import com.codesoom.assignment.exception.UserNotFoundException;
import com.codesoom.assignment.user.application.port.out.DeleteUserPort;
import com.codesoom.assignment.user.application.port.out.GetUserPort;
import com.codesoom.assignment.user.application.port.out.SaveUserPort;
import com.codesoom.assignment.user.domain.User;
import com.github.dozermapper.core.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class UserPersistenceAdapter implements GetUserPort
                                                    , DeleteUserPort
                                                    , SaveUserPort {

    private final UserSpringDataRepository userSpringDataRepository;
    private final Mapper mapper;

    @Override
    public void delete(User user) {
        userSpringDataRepository.delete(mapper.map(user, UserEntity.class));
    }

    @Override
    public User findById(Long id) {
        UserEntity userEntity = userSpringDataRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return mapper.map(userEntity,User.class);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userSpringDataRepository.save(mapper.map(user, UserEntity.class));
        return mapper.map(userEntity, User.class);
    }

}
