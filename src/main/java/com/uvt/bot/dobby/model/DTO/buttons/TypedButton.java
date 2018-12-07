package com.uvt.bot.dobby.model.DTO.buttons;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TypedButton implements Button{

    private final String title;

    private final String type;

    private final String value;
}
