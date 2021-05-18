package com.ulpgc.mycard.dto;

import lombok.Data;

@Data
public class EditStarterCardRequestBody {
    private String userId;
    private String cardName;
}
