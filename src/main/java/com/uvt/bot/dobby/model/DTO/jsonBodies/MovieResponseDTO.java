package com.uvt.bot.dobby.model.DTO.jsonBodies;

import lombok.Data;

import java.util.List;

@Data
public class MovieResponseDTO {

    private List<MovieResultDTO> results;
}
