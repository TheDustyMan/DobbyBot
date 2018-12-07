package com.uvt.bot.dobby.repository;

import com.uvt.bot.dobby.model.entity.Expense;
import org.springframework.data.repository.CrudRepository;

public interface ExpensesRepository extends CrudRepository<Expense, String> {
}
