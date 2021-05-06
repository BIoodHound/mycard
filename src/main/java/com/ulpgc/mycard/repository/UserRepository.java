package com.ulpgc.mycard.repository;

import com.ulpgc.mycard.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);
}
