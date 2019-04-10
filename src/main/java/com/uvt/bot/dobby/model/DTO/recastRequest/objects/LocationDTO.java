package com.uvt.bot.dobby.model.DTO.recastRequest.objects;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class LocationDTO extends GenericObjectDTO{


    private Float lat;
    private Float lng;
    private String type;
    private String place;
    private String country;
}
