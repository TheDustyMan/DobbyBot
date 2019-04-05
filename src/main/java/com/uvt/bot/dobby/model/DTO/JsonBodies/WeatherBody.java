package com.uvt.bot.dobby.model.DTO.JsonBodies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherBody {

    private  String temperature;

    private  String weatherType;


}
