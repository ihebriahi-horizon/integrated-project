package com.example.back.repository;

import com.example.back.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	Optional<Role> findByRoleName(String name);
}
