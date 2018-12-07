package com.uvt.bot.dobby.model.DTO;

import com.uvt.bot.dobby.model.DTO.contents.RecastContent;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TextResponse implements JsonResponse {

    private String type;

    private String content;
}
