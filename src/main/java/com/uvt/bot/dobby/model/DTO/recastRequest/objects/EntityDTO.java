package com.uvt.bot.dobby.model.DTO.recastRequest.objects;

import lombok.Data;

import java.util.List;

@Data
public class EntityDTO {

    List<LocationDTO> location;
    List<DateTimeDTO> datetime;
}
