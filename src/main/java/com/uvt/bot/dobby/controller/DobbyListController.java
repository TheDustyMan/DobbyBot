package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.services.DobbyListService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class DobbyListController {

    private static final Logger logger = LoggerFactory.getLogger(DobbyListController.class);
    private DobbyListService dobbyListService;

    @Autowired
    DobbyListController(DobbyListService dobbyListService) {
        this.dobbyListService = dobbyListService;
    }

    @PostMapping("/getDobbyList")
    public RecastReply getDobbyList(@RequestBody RecastRequestDTO recastRequestDTO) {
        return dobbyListService.getList(recastRequestDTO);
    }

    @PostMapping("/createDobbyList")
    public void createNewDobbyList(@RequestBody RecastRequestDTO recastRequestDTO) {
        dobbyListService.createList(recastRequestDTO);
    }

    @PostMapping("/deleteDobbyList")
    public RecastReply deleteDobbyList(@RequestBody RecastRequestDTO recastRequestDTO){
        return dobbyListService.deleteList(recastRequestDTO);
    }
}
