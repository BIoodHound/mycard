package com.ulpgc.mycard.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.models.BuffShell;
import com.ulpgc.mycard.repository.BuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BuffService {
    @Autowired
    BuffRepository buffRepository;

    public Buff getBuffById(Long id){
        try{
            Buff buff = buffRepository.getById(id);
            return buff;
        }catch (Exception e){
            return null;
        }
    }

    public List<Buff> getBuffs(){
        try{
            return buffRepository.findAll();
        }catch (Exception e){
            return null;
        }
    }

    public BuffShell getBuffShell(List<Buff> buffs){
        if(Objects.isNull(buffs)){
            BuffShell buffShell = new BuffShell(0, 0, false, false);
            return buffShell;
        }
        Integer attack = 0;
        Integer health = 0;
        Boolean windFury = false;
        Boolean divineShield = false;

        for (Buff buff: buffs){
            attack += buff.getAttack_buff();
            health += buff.getHp_buff();
            if(buff.getDivineShield()){
                divineShield = true;
            }
            if (buff.getWindfury()){
                windFury = true;
            }
        }
        BuffShell buffShell = new BuffShell(attack, health, windFury, divineShield);
        return buffShell;
    }
}
