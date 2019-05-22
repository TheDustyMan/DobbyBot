package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpensesController {

    private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);
    private ExpenseService expenseService;

    @Autowired
    ExpensesController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @PostMapping("/getExpense")
    public RecastReply getExpense(@RequestBody RecastRequestDTO recastRequestDTO){
        return expenseService.getExpenses(recastRequestDTO);
    }

    @PostMapping("/createExpense")
    public void createExpense(@RequestBody RecastRequestDTO recastRequestDTO){
        expenseService.createExpense(recastRequestDTO);
    }

    @PostMapping("/deleteExpense")
    public RecastReply deleteExpense(@RequestBody RecastRequestDTO recastRequestDTO){
        return expenseService.deleteExpense(recastRequestDTO);
    }
}
