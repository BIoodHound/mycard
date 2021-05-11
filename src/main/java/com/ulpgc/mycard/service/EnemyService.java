package com.ulpgc.mycard.service;

import com.ulpgc.mycard.dto.AttachBuffDto;
import com.ulpgc.mycard.dto.EnemyCardDto;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.models.Card;
import com.ulpgc.mycard.models.EnemyCard;
import com.ulpgc.mycard.repository.EnemyCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class EnemyService {
    @Autowired
    EnemyCardRepository enemyCardRepository;

    @Autowired
    BuffService buffService;

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

    public int createEnemy(EnemyCardDto enemyCardDto){
        try{
            EnemyCard enemyCard = enemyCardRepository.findEnemyCardByName(enemyCardDto.getName());
            if(Objects.nonNull(enemyCard)){
                return 0;
            }
            if(enemyCardDto.getName().isBlank() ||
                    enemyCardDto.getAttack() == null ||
                    enemyCardDto.getHealth() == null ||
                    enemyCardDto.getImage().isBlank() ||
                    enemyCardDto.getDescription().isBlank() ||
                    enemyCard.getWindfury() ||
                    enemyCardDto.getDivineShield()){
                return 0;
            }
            enemyCard = new EnemyCard();

            enemyCard.setName(enemyCardDto.getName());
            enemyCard.setAttack(enemyCardDto.getAttack());
            enemyCard.setHealth(enemyCardDto.getHealth());
            enemyCard.setImage(enemyCardDto.getImage());
            enemyCard.setDescription(enemyCardDto.getDescription());
            enemyCard.setWindfury(enemyCardDto.getWindfury());
            enemyCard.setDivineShield(enemyCardDto.getDivineShield());

            enemyCardRepository.save(enemyCard);

        }catch (Exception e){
            return 0;
        }
        return 1;
    }

}
