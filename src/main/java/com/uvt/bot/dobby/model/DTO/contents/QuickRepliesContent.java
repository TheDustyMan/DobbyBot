package com.uvt.bot.dobby.model.DTO.contents;

import com.uvt.bot.dobby.model.DTO.buttons.SimpleButton;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class QuickRepliesContent implements RecastContent {

    private String title;

    private ArrayList<SimpleButton> buttons;
}
