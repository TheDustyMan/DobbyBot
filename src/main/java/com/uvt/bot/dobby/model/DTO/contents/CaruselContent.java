package com.uvt.bot.dobby.model.DTO.contents;

import com.uvt.bot.dobby.model.DTO.buttons.Button;
import lombok.Data;

import java.util.List;

@Data
public class CaruselContent implements RecastContent {

    private String title;
    private String subtitle;
    private List<Button> buttons;
    private String imageUrl;
}
