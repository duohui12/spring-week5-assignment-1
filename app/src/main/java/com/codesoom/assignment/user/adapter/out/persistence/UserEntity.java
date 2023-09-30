package com.codesoom.assignment.user.adapter.out.persistence;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
class UserEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    private String password;
}
