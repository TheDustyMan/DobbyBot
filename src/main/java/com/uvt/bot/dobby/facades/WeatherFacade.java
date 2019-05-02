package com.uvt.bot.dobby.facades;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.jsonBodies.WeatherBody;
import com.uvt.bot.dobby.model.DTO.messageType.JsonMessage;
import com.uvt.bot.dobby.model.DTO.messageType.TextMessage;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Component
public class WeatherFacade {

    private WeatherService weatherService;

    public RecastReply getRecastMessage(RecastRequestDTO recastRequestDTO) {

        String location = recastRequestDTO.getNlp().getEntities().getLocation()
                .stream()
                .findFirst()
                .map(locationDTO -> {
                    int index = locationDTO.getFormatted().indexOf(",");
                    return  locationDTO.getFormatted().substring(0, index) + "," + locationDTO.getCountry();
                })
                .orElse("");

        WeatherBody weatherBody = weatherService.getWeather(location);

        String content = weatherBody.getTemperature() + "\n"+ weatherBody.getWeatherType();

        RecastReply recastReply = new RecastReply(new ArrayList<JsonMessage>());

        recastReply.getReplies().add(new TextMessage( content));

        return recastReply;
    }

    @Autowired
    public void setWeatherService(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
}
