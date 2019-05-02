package com.uvt.bot.dobby.services;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class MovieServiceTests {

    @Autowired
    MovieService movieService;

    @Test
    public void constructURLTest(){
        assert "https://api.themoviedb.org/3/discover/movie?sort_by=popularity.desc&with_genres=18&language=en-US&api_key=d7be73a37db6c246f0604a633bcff673".equals(movieService.constructURL("18"));
    }
}
