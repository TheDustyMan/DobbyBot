package com.uvt.bot.dobby.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.uvt.bot.dobby.model.DTO.JsonBodies.WeatherBody;
import com.uvt.bot.dobby.model.DTO.JsonResponse;
import com.uvt.bot.dobby.model.DTO.RecastMessage;
import com.uvt.bot.dobby.model.DTO.TextResponse;
import com.uvt.bot.dobby.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@RestController
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private WeatherService weatherService;
    private  RestTemplate restTemplate;

    @Autowired
    WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @PostMapping ("/getWeather")
    public RecastMessage getWeather(@RequestBody JsonNode jsonNode){

        String location = jsonNode.findValue("raw").asText() + "," + jsonNode.findValue("country").asText();
        WeatherBody weatherBody = weatherService.getWeather(location);

        String content = weatherBody.getTemperature() + "\n"+ weatherBody.getWeatherType();

        RecastMessage recastMessage = new RecastMessage(new ArrayList<JsonResponse>());

        recastMessage.getMessages().add(new TextResponse("text", content));

        return recastMessage;
    }

}
