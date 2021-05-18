package com.ulpgc.mycard.repository;

import com.ulpgc.mycard.models.StarterCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface StarterCardRepository extends JpaRepository<StarterCard, Long> {
    StarterCard findStarterCardByName(String name);
}
