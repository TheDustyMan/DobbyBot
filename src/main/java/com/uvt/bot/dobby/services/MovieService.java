package com.uvt.bot.dobby.services;

import com.uvt.bot.dobby.model.DTO.jsonBodies.MovieResponseDTO;
import com.uvt.bot.dobby.model.DTO.jsonBodies.MovieResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieService {

    private RestTemplate restTemplate;
    private final static String API = "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc";
    private final static String API_LANGUAGE = "&language=en-US";
    private final static String API_KEY = "&api_key=d7be73a37db6c246f0604a633bcff673";
    private final static String ADD_GENRE = "&with_genres=";


    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String constructURL(String genre){
        return API + ADD_GENRE + genre + API_LANGUAGE + API_KEY;
    }

    public MovieResponseDTO getMovie(String url){
        return restTemplate.getForObject(url, MovieResponseDTO.class);
    }

    public String convertMovieGenre(String genre){
        switch (genre){
            case "drama":
                return "18";
            case "action":
                return "28";
            case "adventure":
                return "12";
            case "animation":
                return "16";
            case "fantasy":
                return "14";
            case "comedy":
                return "35";
            case "horror":
                return "27";
            case "romance":
                return "10749";
            case "science-fiction":
                return "878";
            case "thriller":
                return "53";
            case "war":
                return "10752";
            case "western":
                return "37";
            default:
                return "";
        }
    }
}
