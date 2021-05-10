package com.ulpgc.mycard.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Buff> buffs;

}
