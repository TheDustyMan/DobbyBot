package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.DTO.jsonBodies.MovieSugestionBody;
import com.uvt.bot.dobby.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MovieSugestionController {

    private static final Logger logger = LoggerFactory.getLogger(MovieSugestionController.class);
    private MovieService movieService;

    @Autowired
    MovieSugestionController(MovieService movieService){
        this.movieService = movieService;
    }

    @GetMapping("/getMovie")
    public void getMovie(@RequestBody MovieSugestionBody movieSugestionBody){

    }
}
