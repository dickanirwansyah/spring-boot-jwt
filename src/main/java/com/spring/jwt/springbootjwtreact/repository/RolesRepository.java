package com.spring.jwt.springbootjwtreact.repository;

import com.spring.jwt.springbootjwtreact.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, String> {

    Optional<Roles> findByName(String name);
}
