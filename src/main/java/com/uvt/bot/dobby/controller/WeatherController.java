package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.facades.WeatherFacade;
import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    private WeatherFacade weatherFacade;

    @Autowired
    WeatherController(WeatherFacade weatherFacade){
        this.weatherFacade = weatherFacade;
    }

    @PostMapping ("/getWeather")
    public RecastReply getWeather(@RequestBody RecastRequestDTO recastRequestDTO){

        logger.info(recastRequestDTO.toString());
        return weatherFacade.getRecastMessage(recastRequestDTO);
    }

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }




}
