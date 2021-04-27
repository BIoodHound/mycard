package com.ulpgc.mycard.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.repository.BuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
