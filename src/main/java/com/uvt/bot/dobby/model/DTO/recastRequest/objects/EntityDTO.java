package com.uvt.bot.dobby.model.DTO.recastRequest.objects;

import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.*;
import lombok.Data;

import java.util.List;

@Data
public class EntityDTO {

    List<LocationDTO> location;
    List<DateTimeDTO> datetime;
    List<MovieGenreDTO> genre;
    List<ShopItemDTO> shop_item;
    List<MoneyDTO> money;
}
