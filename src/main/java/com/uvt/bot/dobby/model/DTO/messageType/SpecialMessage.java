package com.uvt.bot.dobby.model.DTO.messageType;

import com.uvt.bot.dobby.model.DTO.contents.CaruselContent;
import lombok.Data;

import java.util.List;

@Data
public class SpecialMessage implements JsonMessage {

    private String type;

    private List<CaruselContent> content;
}
