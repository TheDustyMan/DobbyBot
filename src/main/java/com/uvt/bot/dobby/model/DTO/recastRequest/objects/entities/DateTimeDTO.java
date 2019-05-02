package com.uvt.bot.dobby.model.DTO.recastRequest.objects;

import lombok.Data;

@Data
public class DateTimeDTO extends GenericObjectDTO{

    private String iso;
    private String accuracy;
    private String chronology;
    private String state;


}
