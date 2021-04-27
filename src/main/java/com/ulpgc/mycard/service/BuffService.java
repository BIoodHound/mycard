package com.ulpgc.mycard.service;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.ulpgc.mycard.models.Buff;
import com.ulpgc.mycard.repository.BuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuffService {
    @Autowired
    BuffRepository buffRepository;

    Buff getBuffById(Long id){
        try{
            Buff buff = buffRepository.getById(id);
            return buff;
        }catch (Exception e){
            return null;
        }
    }
}
