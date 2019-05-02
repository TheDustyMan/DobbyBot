package com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities;

import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.GenericObjectDTO;
import lombok.Data;

@Data
public class LocationDTO extends GenericObjectDTO {


    private Float lat;
    private Float lng;
    private String type;
    private String place;
    private String country;
}
