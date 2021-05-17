package com.ulpgc.mycard.models;

import lombok.Data;

@Data
public class BuffShell {
    private Integer attack;
    private Integer health;
    private Boolean windFury;
    private Boolean divineShield;

    public BuffShell(Integer attack, Integer health, Boolean windFury, Boolean divineShield) {
        this.attack = attack;
        this.health = health;
        this.windFury = windFury;
        this.divineShield = divineShield;
    }
}
