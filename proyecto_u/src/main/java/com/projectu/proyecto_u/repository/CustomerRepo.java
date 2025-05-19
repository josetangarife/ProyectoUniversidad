package com.projectu.proyecto_u.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projectu.proyecto_u.models.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
Optional<Customer> findByUsernameOrEmail(String username, String email);

    Boolean existsByUsername(String username);

    Optional<Customer> findByUsername(String username); 

    Boolean existsByEmail(String email); 

}
