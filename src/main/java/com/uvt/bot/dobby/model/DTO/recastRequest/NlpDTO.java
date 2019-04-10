package com.uvt.bot.dobby.model.DTO.recastRequest;

import com.uvt.bot.dobby.model.DTO.recastRequest.objects.EntityDTO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class NlpDTO {

    private String act;
    private EntityDTO entities;
    private List<Object> intents;
    private String language;
    private String processing_language;
    private String sentiment;
    private String source;
    private String status;
    private String timestamp;
    private String type;
    private String uuid;
    private String version;

}
