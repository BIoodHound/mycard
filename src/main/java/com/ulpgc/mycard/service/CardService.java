package com.ulpgc.mycard.service;

import com.ulpgc.mycard.dto.AttachBuffDto;
import com.ulpgc.mycard.dto.PlayerCardDto;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.models.BuffShell;
import com.ulpgc.mycard.models.Card;
import com.ulpgc.mycard.models.Users;
import com.ulpgc.mycard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    @Autowired
    BuffService buffService;

    public Card getCard(Long id){
        Card card = cardRepository.findCardByCardId(id);
        return card;
    }

    public PlayerCardDto getCardBuffCalc(Long id){
        Card card = getCard(id);
        if(Objects.isNull(card)){
            return null;
        }
        Integer attack = card.getAttack();
        Integer health = card.getHealth();
        Boolean hasWindFury = false;
        Boolean hasDivineShield = false;
        Integer size = card.getBuffs().size();
        if(size>1){
            BuffShell buffShell = buffService.getBuffShell((List<Buff>) card.getBuffs());
            attack = card.getAttack() + buffShell.getAttack();
            health = card.getHealth() + buffShell.getHealth();
            hasWindFury = buffShell.getWindFury();
            hasDivineShield = buffShell.getDivineShield();
        }

        PlayerCardDto playerCardDto = new PlayerCardDto(
                card.getName(),
                attack,
                health,
                card.getImage(),
                hasWindFury,
                hasDivineShield
        );
        return playerCardDto;
    }

    public Boolean createCard(Users user){
        try {
            Card card = new Card();
            card.setBuffs(new HashSet<>());
            card.setName("angry fox");
            card.setAttack(1);
            card.setHealth(3);
            card.setImage("https://i2-prod.mirror.co.uk/incoming/article8150610.ece/ALTERNATES/s1200b/PAY-Angry-birds.jpg");

            card.setUser(user);
            cardRepository.save(card);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public Boolean addBuff(AttachBuffDto attachBuffDto){
        try {
            Buff buff = buffService.getBuffById(attachBuffDto.getBuffId());
            Card card = cardRepository.findCardByCardId(Long.valueOf(attachBuffDto.getUserId()));

            Set<Buff> buffs = card.getBuffs();
            buffs.add(buff);
            card.setBuffs(buffs);

            cardRepository.save(card);

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Boolean removeBuff(AttachBuffDto removeBuffDto){
        try {
            Buff buff = buffService.getBuffById(removeBuffDto.getBuffId());
            Card card = cardRepository.findCardByCardId(Long.valueOf(removeBuffDto.getUserId()));

            Set<Buff> buffs = card.getBuffs();
            buffs.remove(buff);
            card.setBuffs(buffs);

            cardRepository.save(card);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public Set<Buff> getCardBuffs(Long id){
        try{
            Card card = cardRepository.findCardByCardId(id);
            return card.getBuffs();
        } catch (Exception e){
            return null;
        }
    }
}
