package com.codesoom.assignment.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserSpringDataRepository extends JpaRepository<UserEntity, Long> {
}
