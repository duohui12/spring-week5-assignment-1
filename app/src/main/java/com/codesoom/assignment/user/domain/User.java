package com.codesoom.assignment.user.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;

    private String email;

    private String name;

    private String password;

    public void change(String name,
                       String password) {
        if(name != null ) this.name = name;
        if(password != null) this.password = password;
    }
}
