package com.ulpgc.mycard.repository;

import com.ulpgc.mycard.models.EnemyCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface EnemyCardRepository extends JpaRepository<EnemyCard, Long> {
    EnemyCard findEnemyCardByEnemyId(Long id);
}
