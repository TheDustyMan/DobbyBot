package com.uvt.bot.dobby.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.uvt.bot.dobby.model.DTO.JsonBodies.WeatherBody;
import com.uvt.bot.dobby.model.DTO.JsonResponse;
import com.uvt.bot.dobby.model.DTO.TextResponse;
import com.uvt.bot.dobby.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private WeatherService weatherService;

    @Autowired
    WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @PostMapping ("/getWeather")
    public JsonResponse getWeather(@RequestBody JsonNode jsonNode){

        String location = jsonNode.findValue("location").asText() + "," + jsonNode.findValue("country").asText();
        WeatherBody weatherBody = weatherService.getWeather(location);

        String content = weatherBody.getTemperature() + "\n"+ weatherBody.getWeatherType();

        return new TextResponse("text", content);
    }

}
