package com.uvt.bot.dobby.model.DTO.recastRequest;

import lombok.Data;

@Data
public class RecastRequestDTO {

    private String action_id;
    private ConversationDTO conversation;
    private NlpDTO nlp;
}
