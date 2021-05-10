package com.ulpgc.mycard.service;

import com.ulpgc.mycard.models.EnemyCard;
import com.ulpgc.mycard.repository.EnemyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnemyService {
    @Autowired
    EnemyCardRepository enemyCardRepository;

    public EnemyCard getEnemyCard(Long id){
        try {
            return enemyCardRepository.findEnemyCardByEnemyId(id);
        }catch (Exception e){
            return null;
        }
    }

    public List<EnemyCard> getEnemyCards(){
        try {
            return enemyCardRepository.findAll();
        } catch (Exception e){
            return null;
        }
    }
}
