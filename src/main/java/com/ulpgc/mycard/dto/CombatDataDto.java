package com.ulpgc.mycard.dto;

import com.ulpgc.mycard.models.CombatLog;
import lombok.Data;

import java.util.List;

@Data
public class CombatDataDto {
    public String result;
    public PlayerCardDto playerCardDto;
    public EnemyCardDto enemyCardDto;
    public List<CombatLog> combatLogList;

    public CombatDataDto(String result, PlayerCardDto playerCardDto, EnemyCardDto enemyCardDto, List<CombatLog> combatLogList) {
        this.result = result;
        this.playerCardDto = playerCardDto;
        this.enemyCardDto = enemyCardDto;
        this.combatLogList = combatLogList;
    }
}
