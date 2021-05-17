package com.ulpgc.mycard.service;

import com.ulpgc.mycard.dto.CombatDataDto;
import com.ulpgc.mycard.dto.CombatRequestDto;
import com.ulpgc.mycard.dto.EnemyCardDto;
import com.ulpgc.mycard.dto.PlayerCardDto;
import com.ulpgc.mycard.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CombatService {

    @Autowired
    CardService cardService;

    @Autowired
    EnemyService enemyService;

    public CombatDataDto combat(CombatRequestDto combatRequestDto){
        CombatDataDto combatDataDto;
        if(Objects.isNull(combatRequestDto)){
            return null;
        }
        PlayerCardDto userCard = getUserCard(combatRequestDto.getUserId());
        if(Objects.isNull(userCard)){
            return null;
        }
        EnemyCardDto enemyCardDto = getEnemyCard(combatRequestDto.getEnemyId());
        if(Objects.isNull(enemyCardDto)){
            return null;
        }
        CombatCalcResult combatCalcResult = combatCalc(userCard, enemyCardDto);
        if(Objects.isNull(combatCalcResult)){
            return null;
        }
        combatDataDto = new CombatDataDto(
                combatCalcResult.getResult(),
                userCard,
                enemyCardDto,
                combatCalcResult.getCombatLogList()
        );
        return combatDataDto;
    }
    private CombatCalcResult combatCalc(PlayerCardDto userCard, EnemyCardDto enemyCardDto) {
        String result = "Victory";

        List<CombatLog> combatLogs = new ArrayList<>();


        Integer userAttack = userCard.getAttack();
        Integer userHealth = userCard.getHealth();

        Integer enemyCardAttack = enemyCardDto.getAttack();
        Integer enemyCardHealth = enemyCardDto.getHealth();

        while (userHealth > 0 || enemyCardHealth > 0){
            if(userCard.getWindFury()){
                if(enemyCardDto.getDivineShield()){
                    enemyCardHealth = enemyCardHealth - userAttack;
                    enemyCardDto.setDivineShield(false);
                    String log = userCard.getName() + " attacked twice and removed " + enemyCardDto.getName()
                            + "´s shield and dealt " + userAttack + " damage";
                    CombatLog combatLog = new CombatLog("player", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                }else {
                    enemyCardHealth = enemyCardHealth - (userAttack * 2);
                    String log = userCard.getName() + " attacked " + enemyCardDto.getName()
                            + " twice and dealt " + (userAttack * 2) + " damage";
                    CombatLog combatLog = new CombatLog("player", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                }
            } else {
                if(enemyCardDto.getDivineShield()){
                    enemyCardDto.setDivineShield(false);
                    String log = userCard.getName() + " attacked " + enemyCardDto.getName()
                            + " and remove his shield";
                    CombatLog combatLog = new CombatLog("player", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                }else{
                    enemyCardHealth = enemyCardHealth - userAttack;
                    String log = userCard.getName() + " attacked " + enemyCardDto.getName()
                            + " and dealt " + userAttack + " damage";
                    CombatLog combatLog = new CombatLog("player", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }

                }
            }

            if(enemyCardDto.getWindfury()){
                if(userCard.getDivineShield()){
                    userHealth = userHealth - enemyCardAttack;
                    userCard.setDivineShield(false);
                    String log = enemyCardDto.getName() + " attacked twice and removed " + userCard.getName()
                            + "´s shield and dealt " + userAttack + " damage";
                    CombatLog combatLog = new CombatLog("enemyCard", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                } else {
                    userHealth = userHealth - (enemyCardAttack * 2);
                    String log = enemyCardDto.getName() + " attacked " + userCard.getName()
                            + " twice and dealt " + (userAttack * 2) + " damage";
                    CombatLog combatLog = new CombatLog("enemyCard", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                }
            } else {
                if(userCard.getDivineShield()){
                    userCard.setDivineShield(false);
                    String log = enemyCardDto.getName() + " attacked " + userCard.getName()
                            + " and remove his shield";
                    CombatLog combatLog = new CombatLog("enemyCard", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                } else {
                    userHealth = userHealth - enemyCardAttack;
                    String log = enemyCardDto.getName() + " attacked " + userCard.getName()
                            + " and dealt " + userAttack + "damage";
                    CombatLog combatLog = new CombatLog("enemyCard", log);
                    combatLogs.add(combatLog);
                    if(userHealth < 0 || enemyCardHealth < 0){
                        break;
                    }
                }
            }
        }
        if(userHealth < 0){
            result = "Defeat";
        } else {
            result = "Victory";
        }
        CombatCalcResult combatCalcResult = new CombatCalcResult(result, combatLogs);
        return combatCalcResult;
    }

    private EnemyCardDto getEnemyCard(String enemyId) {
        Long id = Long.valueOf(enemyId);
        EnemyCard enemyCard = enemyService.getEnemyCard(id);
        if(Objects.isNull(enemyCard)){
            return null;
        }
        EnemyCardDto enemyCardDto = new EnemyCardDto(
                enemyCard.getName(),
                enemyCard.getAttack(),
                enemyCard.getHealth(),
                enemyCard.getImage(),
                enemyCard.getDescription(),
                enemyCard.getWindfury(),
                enemyCard.getDivineShield()
        );
        return enemyCardDto;
    }

    private PlayerCardDto getUserCard(String userId) {
        Long id = Long.valueOf(userId);
        PlayerCardDto card = cardService.getCardBuffCalc(id);
        if(Objects.isNull(card)){
            return null;
        }
        return card;
    }
}
