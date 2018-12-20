package com.uvt.bot.dobby.model.DTO;

import com.uvt.bot.dobby.model.DTO.messageType.JsonMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class RecastReply {

    private ArrayList<JsonMessage> replies;
}
