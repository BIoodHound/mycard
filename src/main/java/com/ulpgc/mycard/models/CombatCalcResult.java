package com.ulpgc.mycard.models;

import lombok.Data;

import java.util.List;

@Data
public class CombatCalcResult {
    private String result;
    private List<CombatLog> combatLogList;

    public CombatCalcResult(String result, List<CombatLog> combatLogList) {
        this.result = result;
        this.combatLogList = combatLogList;
    }
}
