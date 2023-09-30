package com.codesoom.assignment.user.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void change() {
        //given
        User user = new User();
        user.setId(1L);
        user.setEmail("dd@gmail.com");
        user.setName("test name");
        user.setPassword("1111");

        User source = new User();
        source.setName("new name");

        //when
        user.change(source.getName(), source.getPassword());

        //then
        assertThat(user.getName()).isEqualTo("new name");
        assertThat(user.getId()).isEqualTo(1L);
        assertThat(user.getPassword()).isEqualTo("1111");
    }
}
