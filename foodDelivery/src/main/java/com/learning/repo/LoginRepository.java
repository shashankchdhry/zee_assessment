package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	boolean existsByEmail(String email);
}
