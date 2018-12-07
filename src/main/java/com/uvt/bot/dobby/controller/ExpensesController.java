package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.entity.Expense;
import com.uvt.bot.dobby.repository.ExpensesRepository;
import com.uvt.bot.dobby.services.WeatherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class ExpensesController {

    private static final Logger logger = LoggerFactory.getLogger(ExpensesController.class);
    private ExpensesRepository expensesRepository;

    @Autowired
    ExpensesController(ExpensesRepository expensesRepository){
        this.expensesRepository = expensesRepository;
    }

    /*@GetMapping("/getExpense")
    public List<Expense> getExpense(@RequestBody CustomDate date){

        return expensesRepository.getCustomDate();
    }*/

    @PostMapping("/createExpense")
    public Expense createExpense(@RequestBody Expense expense){
        return expensesRepository.save(expense);
    }

    @DeleteMapping("/deleteExpense")
    public void deleteExpense(@RequestBody Expense expense){
        Optional<Expense> expenseToDelete = expensesRepository.findById(expense.getId());
        expensesRepository.delete(expenseToDelete.get());
    }
}
