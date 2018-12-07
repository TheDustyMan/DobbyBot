package com.uvt.bot.dobby.model.DTO.buttons;

import lombok.Data;

@Data
public class SimpleButton implements Button{

    private final String title;

    private final String value;
}
