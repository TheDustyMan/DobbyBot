package com.uvt.bot.dobby.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class RecastReply {

    private ArrayList<JsonResponse> replies;
}
