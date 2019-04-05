package com.uvt.bot.dobby.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.uvt.bot.dobby.model.DTO.JsonBodies.WeatherBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.text.DecimalFormat;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    private final static String API = "http://api.openweathermap.org/data/2.5/weather?q=";
    private final static String APPID = "f28d2c1d609bc692988c3ed377fbd01f";
    private final static String ERROR_MSG = "I don't know the weather today!";
    private static DecimalFormat format2Decimals = new DecimalFormat(".#");
    private RestTemplate restTemplate;

    @Autowired
    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    private String addLocation(String location) {
        return API + location + "&APPID=" + APPID;
    }

    public WeatherBody getWeather(String location) {
        try {
            JsonNode jsonNode = restTemplate.getForObject(addLocation(location), JsonNode.class);
            return createWeatherBody(jsonNode);
        }catch (RestClientException e){
            return new WeatherBody("", ERROR_MSG);
        }

    }

    private WeatherBody createWeatherBody(JsonNode jsonNode) {

        Double temperature = jsonNode.findValue("temp").asDouble();
        String weatherType = jsonNode.findValue("main").asText();

        return new WeatherBody(correctTemperature(temperature), correctWeatherType(weatherType));
    }

    private String correctTemperature(Double temperature) {
        if (temperature != 0.0)
        temperature = temperature - 273;
        return format2Decimals.format(temperature) + "\u00b0" + "C";
    }

    private String correctWeatherType(String weatherType) {
        if ( weatherType == null) weatherType = ERROR_MSG;
        if (weatherType.equals("Clear")) weatherType = "Sunny";
        return weatherType;
    }
}
