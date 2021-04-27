package com.ulpgc.mycard.repository;

import com.ulpgc.mycard.models.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findCardByCardId(Long id);
}
