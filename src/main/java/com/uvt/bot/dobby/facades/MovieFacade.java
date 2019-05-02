package com.uvt.bot.dobby.facades;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.buttons.Button;
import com.uvt.bot.dobby.model.DTO.buttons.TypedButton;
import com.uvt.bot.dobby.model.DTO.contents.CaruselContent;
import com.uvt.bot.dobby.model.DTO.contents.QuickRepliesContent;
import com.uvt.bot.dobby.model.DTO.jsonBodies.MovieResultDTO;
import com.uvt.bot.dobby.model.DTO.messageType.JsonMessage;
import com.uvt.bot.dobby.model.DTO.messageType.SpecialMessage;
import com.uvt.bot.dobby.model.DTO.messageType.TextMessage;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.MovieGenreDTO;
import com.uvt.bot.dobby.services.MovieService;
import com.uvt.bot.dobby.util.GenericBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieFacade {

    private MovieService movieService;
    private final static String IMAGE_ROOT_PATH = "https://image.tmdb.org/t/p/w1280";

    // TODO: 25/04/2019 Work on this
    public RecastReply getRecastMessage(RecastRequestDTO recastRequestDTO) {

        String genre = recastRequestDTO.getNlp().getEntities().getGenre()
                .stream()
                .findFirst()
                .map(MovieGenreDTO::getValue)
                .orElse("");

        String url = movieService.constructURL(handleGenre(genre));
        List<CaruselContent> carousel = movieService.getMovie(url).getResults().stream()
                .limit(3)
                .map((this::mapToCarusel)).collect(Collectors.toList());


        RecastReply recastReply = new RecastReply(new ArrayList<>());

        SpecialMessage caruselMessage = new SpecialMessage();
        caruselMessage.setType("carousel");
        caruselMessage.setContent(carousel);

        recastReply.getReplies().add(caruselMessage);

        return recastReply;
    }

    private CaruselContent mapToCarusel(MovieResultDTO movieResultDTO){

        List<Button> buttons = new ArrayList<>();
        TypedButton button = new TypedButton("Go to site", "web_url", "https://www.cinemacity.ro/search?query=" + movieResultDTO.getTitle());
        buttons.add(button);

        return GenericBuilder.of(CaruselContent::new)
                .with(CaruselContent::setTitle, movieResultDTO.getTitle())
                .with(CaruselContent::setImageUrl, IMAGE_ROOT_PATH + movieResultDTO.getPoster_path())
                .with(CaruselContent::setSubtitle, movieResultDTO.getOverview())
                .with(CaruselContent::setButtons, buttons)
                .build();

    }

    private String handleGenre(String genre){
        String[] genres = genre.split(" ");
        StringBuilder convertedMovieGenre = new StringBuilder();
        for (String movieGenre : genres){
           convertedMovieGenre.append(movieService.convertMovieGenre(movieGenre)).append(",");
        }
        return convertedMovieGenre.toString();
    }

    @Autowired
    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
