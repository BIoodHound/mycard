package com.ulpgc.mycard.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "buff")
public class Buff {
    @Id
    @Column(name = "buff_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String image;

    private String description;

    private Integer hp_buff;

    private Integer attack_buff;

    private Boolean windfury;

    private Boolean divineShield;

    @ManyToMany
    private Set<Card> card;
}