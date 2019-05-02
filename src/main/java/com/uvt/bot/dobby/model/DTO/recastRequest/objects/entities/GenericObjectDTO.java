package com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities;

import lombok.Data;

@Data
public class GenericObjectDTO {

    private String formatted;
    private String raw;
    private Float confidence;
}
