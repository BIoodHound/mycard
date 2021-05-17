package com.ulpgc.mycard.controller;

import com.ulpgc.mycard.dto.CombatDataDto;
import com.ulpgc.mycard.dto.CombatRequestDto;
import com.ulpgc.mycard.service.CombatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
public class CombatController {

    @Autowired
    CombatService combatService;

    @PostMapping("api/combat")
    public ResponseEntity<?> combat(@RequestBody CombatRequestDto combatRequestDto){
        try {
            CombatDataDto combat = combatService.combat(combatRequestDto);
            if(Objects.nonNull(combat)){
                return ResponseEntity.ok(combat);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
        return ResponseEntity.badRequest().body("bad");
    }
}
