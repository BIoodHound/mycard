package com.ulpgc.mycard.dto;

import lombok.Data;

@Data
public class EnemyCardDto {
    public String name;

    private Integer attack;

    private Integer health;

    private String image;
}
