package com.ms.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.ms.user.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel, UUID> {

	public Optional<UserModel> findByEmailContainingIgnoreCaseOrderByName(@Param("email") String email);
}
