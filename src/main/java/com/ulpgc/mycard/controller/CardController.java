package com.ulpgc.mycard.controller;

import com.ulpgc.mycard.dto.AttachBuffDto;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.models.Card;
import com.ulpgc.mycard.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
public class CardController {
    @Autowired
    CardService cardService;

    @GetMapping("api/card/{card_id}")
    public ResponseEntity<?> getCard(@PathVariable("card_id") Long id){
        try{
            Card card =  cardService.getCard(id);
            if(card.getCardId()==null){
                return ResponseEntity.badRequest().body("bad");
            }
            return ResponseEntity.ok(card);

        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }
    @PostMapping("api/card/addbuff")
    public ResponseEntity<?> addBuff(@RequestBody AttachBuffDto attachBuffDto){
        try{
            if(cardService.addBuff(attachBuffDto)){
                return ResponseEntity.ok("buff attached");
            }
            return ResponseEntity.badRequest().body("bad");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @PostMapping("api/card/removebuff")
    public ResponseEntity<?> removeBuff(@RequestBody AttachBuffDto attachBuffDto){
        try{
            if(cardService.removeBuff(attachBuffDto)){
                return ResponseEntity.ok("buff removed");
            }
            return ResponseEntity.badRequest().body("bad");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @GetMapping("api/card/buffs/{card_id}")
    public ResponseEntity<?> getCardBuffs(@PathVariable("card_id") Long id){
        try {
            Set<Buff> buff = cardService.getCardBuffs(id);
            if(!Objects.isNull(buff)){
                return ResponseEntity.ok(buff);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
        return ResponseEntity.badRequest().body("bad");
    }


}
