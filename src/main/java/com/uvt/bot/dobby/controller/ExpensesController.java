package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.services.EntityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpensesController {

    private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);
    private EntityService entityService;

    @Autowired
    ExpensesController(EntityService entityService){
        this.entityService = entityService;
    }

    @PostMapping("/getExpense")
    public RecastReply getExpense(@RequestBody RecastRequestDTO recastRequestDTO){
        return null;
    }

    @PostMapping("/createExpense")
    public RecastReply createExpense(@RequestBody RecastRequestDTO recastRequestDTO){
        return null;
    }

    @PostMapping("/deleteExpense")
    public RecastReply deleteExpense(@RequestBody RecastRequestDTO recastRequestDTO){
        return null;
    }
}
