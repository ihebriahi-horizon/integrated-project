package com.example.back.repository;

import com.example.back.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
	Optional<User> findByUserEmail(String email);

    Boolean existsByUserEmail(String email);
}
