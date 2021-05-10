package com.ulpgc.mycard.controller;

import com.ulpgc.mycard.dto.AttachBuffDto;
import com.ulpgc.mycard.dto.EnemyCardDto;
import com.ulpgc.mycard.models.EnemyCard;
import com.ulpgc.mycard.service.EnemyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
public class EnemyController {
    @Autowired
    EnemyService enemyService;

    @GetMapping("api/enemy/{enemy_id}")
    public ResponseEntity<?> getEnemyCard(@PathVariable("enemy_id")Long id){
        try{
            EnemyCard enemyCard = enemyService.getEnemyCard(id);
            if(Objects.isNull(enemyCard)){
                return ResponseEntity.badRequest().body("bad");
            }
            return ResponseEntity.ok(enemyCard);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @GetMapping("api/enemy")
    public ResponseEntity<?> getEnemies(){
        try {
            List<EnemyCard> enemyCards = enemyService.getEnemyCards();
            if(Objects.isNull(enemyCards)){
                return ResponseEntity.badRequest().body("bad");
            }
            return ResponseEntity.ok(enemyCards);
        }catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @PostMapping("api/enemy")
    public ResponseEntity<?> createEnemy(@RequestBody EnemyCardDto enemyCardDto){
        try{
            if(enemyService.createEnemy(enemyCardDto) == 1){
                return ResponseEntity.ok("Enemy Card create Successfully");
            }
            return ResponseEntity.badRequest().body("bad");
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.badRequest().body("bad");
        }
    }

    @PostMapping("api/enemy/addbuff/{enemy_id}")
    public ResponseEntity<?> addBuff(@RequestBody AttachBuffDto attachBuffDto){
        try{
            if(enemyService.addBuff(attachBuffDto)){
                return ResponseEntity.ok("buff added");
            }
        }catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
        return ResponseEntity.badRequest().body("bad");
    }

    @PostMapping("api/enemy/removebuff/{enemy_id}")
    public ResponseEntity<?> removeBuff(@RequestBody AttachBuffDto attachBuffDto){
        try{
            if(enemyService.removeBuff(attachBuffDto)){
                return ResponseEntity.ok("buff removed");
            }
            return ResponseEntity.badRequest().body("bad");

        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
    }
}
