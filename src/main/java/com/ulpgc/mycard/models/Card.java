package com.ulpgc.mycard.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cardId;

    private String name;

    private Integer attack;

    private Integer health;

    private String image;

    @OneToOne(cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Buff> buffs;

    public Card(Users user){
        this.user = user;
    }

    public Card() {

    }
}