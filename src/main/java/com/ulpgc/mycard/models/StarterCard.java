package com.ulpgc.mycard.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class StarterCard {
    @Id
    private Long cardId;
    private String name;
    private Integer attack;
    private Integer health;
    private String image;
}
