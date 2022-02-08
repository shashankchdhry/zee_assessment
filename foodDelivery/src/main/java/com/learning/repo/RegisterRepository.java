package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Register;

@Repository
public interface RegisterRepository extends JpaRepository<Register, Integer> {

	boolean existsByEmail(String email);

}
