package com.ulpgc.mycard.dto;

import lombok.Data;

@Data
public class PlayerCardDto {
    public String name;
    public Integer attack;
    public Integer health;
    public String image;
    public Boolean windFury;
    public Boolean divineShield;


    public PlayerCardDto(String name,
                         Integer attack,
                         Integer health,
                         String image,
                         Boolean windFury,
                         Boolean divineShield) {
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.image = image;
        this.windFury = windFury;
        this.divineShield = divineShield;
    }
}
