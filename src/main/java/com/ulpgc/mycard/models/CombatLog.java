package com.ulpgc.mycard.models;

import lombok.Data;

@Data
public class CombatLog {
    private String state;
    private String log;

    public CombatLog(String state, String log) {
        this.state = state;
        this.log = log;
    }
}
