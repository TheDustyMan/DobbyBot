package com.uvt.bot.dobby.model.DTO.messageType;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextMessage implements JsonMessage {

    private final String type = "text";

    private String content;
}
