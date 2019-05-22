package com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities;

import lombok.Data;

@Data
public class MoneyDTO {

    private Double amount;
    private String currency;
    private Double dollars;

}
