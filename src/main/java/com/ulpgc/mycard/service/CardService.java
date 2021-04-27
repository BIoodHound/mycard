package com.ulpgc.mycard.service;

import com.ulpgc.mycard.dto.AttachBuffDto;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.models.Card;
import com.ulpgc.mycard.models.Users;
import com.ulpgc.mycard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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

    public Boolean createCard(Users user){
        try {
            Card card = new Card();
            card.setBuffs(new HashSet<>());
            card.setName("angry fox");
            card.setAttack(1);
            card.setHealth(3);
            card.setImage("");

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
