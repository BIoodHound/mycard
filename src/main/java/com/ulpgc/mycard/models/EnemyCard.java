package com.ulpgc.mycard.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EnemyCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long enemyId;

    private String name;

    private Integer attack;

    private Integer health;

    private String image;

    private String description;

    private Boolean windfury;

    private Boolean divineShield;

}
