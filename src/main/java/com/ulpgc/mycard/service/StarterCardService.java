package com.ulpgc.mycard.service;

import com.ulpgc.mycard.dto.EditStarterCardRequestBody;
import com.ulpgc.mycard.dto.StarterCardDto;
import com.ulpgc.mycard.models.Card;
import com.ulpgc.mycard.models.StarterCard;
import com.ulpgc.mycard.repository.StarterCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StarterCardService {

    @Autowired
    StarterCardRepository starterCardRepository;

    @Autowired
    CardService cardService;

    public List<StarterCardDto> getStarterCards(){
        List<StarterCardDto> cardDtos = new ArrayList<>();
        try {
            List<StarterCard> starterCards = starterCardRepository.findAll();
            if(starterCards.isEmpty()){
                return null;
            }
            for(StarterCard starterCard : starterCards){
                StarterCardDto starterCardDto = new StarterCardDto();
                starterCardDto.setName(starterCard.getName());
                starterCardDto.setAttack(starterCard.getAttack());
                starterCardDto.setHealth(starterCard.getHealth());
                starterCardDto.setImage(starterCard.getImage());

                cardDtos.add(starterCardDto);
            }
            return cardDtos;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

    public Boolean setStarterCard(EditStarterCardRequestBody editStarterCardRequestBody){
        if(Objects.isNull(editStarterCardRequestBody)){
            return false;
        }
        try {
            StarterCard starterCardByName = starterCardRepository.findStarterCardByName(editStarterCardRequestBody.getCardName());
            if(Objects.isNull(starterCardByName)){
                return false;
            }
            Card cardServiceCard = cardService.getCard(Long.valueOf(editStarterCardRequestBody.getUserId()));
            if(Objects.isNull(cardServiceCard)){
                return false;
            }
            cardServiceCard.setName(starterCardByName.getName());
            cardServiceCard.setAttack(starterCardByName.getAttack());
            cardServiceCard.setHealth(starterCardByName.getHealth());
            cardServiceCard.setImage(starterCardByName.getImage());

            cardService.cardRepository.save(cardServiceCard);

            return true;
        }catch (Exception e){
            System.out.println(e);
            return null;
        }
    }

}
