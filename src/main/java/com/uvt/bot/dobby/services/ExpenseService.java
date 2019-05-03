package com.uvt.bot.dobby.services;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.model.entity.Expense;
import com.uvt.bot.dobby.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private ExpensesRepository expensesRepository;

    @Autowired
    public ExpenseService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public RecastReply createExpense(RecastRequestDTO recastRequestDTO){
        Expense expense = expensesRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseGet(Expense::new);

        return  null;
    }
}
