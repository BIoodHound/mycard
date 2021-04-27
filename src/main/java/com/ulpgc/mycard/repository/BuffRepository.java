package com.ulpgc.mycard.repository;

import com.ulpgc.mycard.models.Buff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface BuffRepository extends JpaRepository<Buff, Long> {
    Buff getById(Long id);
    Buff getByName(String name);
}
