package com.uvt.bot.dobby.model.DTO.recastRequest.objects;

import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.DateTimeDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.LocationDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.MovieGenreDTO;
import lombok.Data;

import java.util.List;

@Data
public class EntityDTO {

    List<LocationDTO> location;
    List<DateTimeDTO> datetime;
    List<MovieGenreDTO> genre;
}
