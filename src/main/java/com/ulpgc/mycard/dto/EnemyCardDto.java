package com.ulpgc.mycard.dto;

import lombok.Data;

@Data
public class EnemyCardDto {
    public String name;

    private Integer attack;

    private Integer health;

    private String image;

    private String description;

    private Boolean windfury;

    private Boolean divineShield;

    public EnemyCardDto(String name, Integer attack, Integer health, String image, String description, Boolean windfury, Boolean divineShield) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.image = image;
        this.description = description;
        this.windfury = windfury;
        this.divineShield = divineShield;
    }
}
