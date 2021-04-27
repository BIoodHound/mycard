package com.ulpgc.mycard.controller;

import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.service.BuffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*")
@RestController
public class BuffController {

    @Autowired
    BuffService buffService;

    @GetMapping("api/buff")
    public ResponseEntity<?> getBuffs(){
        try {
            List<Buff> buff = buffService.getBuffs();
            if(!Objects.isNull(buff)){
                return ResponseEntity.ok(buff);
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().body("bad");
        }
        return ResponseEntity.badRequest().body("bad");
    }

}
