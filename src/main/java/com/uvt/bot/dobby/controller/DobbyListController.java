package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.model.entity.DobbyList;
import com.uvt.bot.dobby.model.entity.DobbyListItem;
import com.uvt.bot.dobby.repository.DobbyListRepository;
import com.uvt.bot.dobby.services.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class DobbyListController {

    private static final Logger logger = LoggerFactory.getLogger(DobbyListController.class);
    private EntityService entityService;

    @Autowired
    DobbyListController(EntityService entityService) {
        this.entityService = entityService;
    }

    @PostMapping("/getDobbyList")
    public RecastReply getDobbyList(@RequestBody RecastRequestDTO recastRequestDTO) {
        return entityService.getList(recastRequestDTO);
    }

    @PostMapping("/createDobbyList")
    public void createNewDobbyList(@RequestBody RecastRequestDTO recastRequestDTO) {
        entityService.createList(recastRequestDTO);
    }

    @PostMapping("/deleteDobbyList")
    public RecastReply deleteDobbyList(@RequestBody RecastRequestDTO recastRequestDTO){
        return entityService.deleteList(recastRequestDTO);
    }
}
