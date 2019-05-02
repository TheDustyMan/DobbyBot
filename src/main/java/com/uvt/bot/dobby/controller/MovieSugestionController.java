package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.facades.MovieFacade;
import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.jsonBodies.MovieResultDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieSugestionController {

    private static final Logger logger = LoggerFactory.getLogger(MovieSugestionController.class);
    private MovieFacade movieFacade;

    @Autowired
    MovieSugestionController(MovieFacade movieFacade){
        this.movieFacade = movieFacade;
    }

    @PostMapping("/getMovie")
    public RecastReply getMovie(@RequestBody RecastRequestDTO recastRequestDTO){
        return movieFacade.getRecastMessage(recastRequestDTO);
    }
}
