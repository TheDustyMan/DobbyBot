package com.uvt.bot.dobby.model.DTO.buttons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class TypedButton implements Button{

    private String title;

    private String type;

    private String value;
}
