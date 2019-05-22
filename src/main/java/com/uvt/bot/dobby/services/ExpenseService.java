package com.uvt.bot.dobby.services;

import com.uvt.bot.dobby.model.DTO.RecastReply;
import com.uvt.bot.dobby.model.DTO.messageType.TextMessage;
import com.uvt.bot.dobby.model.DTO.recastRequest.RecastRequestDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.DateTimeDTO;
import com.uvt.bot.dobby.model.DTO.recastRequest.objects.entities.MoneyDTO;
import com.uvt.bot.dobby.model.entity.Expense;
import com.uvt.bot.dobby.model.entity.ExpenseItem;
import com.uvt.bot.dobby.repository.ExpensesRepository;
import com.uvt.bot.dobby.util.GenericBuilder;
import com.uvt.bot.dobby.util.exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private ExpensesRepository expensesRepository;

    @Autowired
    public ExpenseService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public void createExpense(RecastRequestDTO recastRequestDTO){
        Expense expense = expensesRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseGet(Expense::new);
        final String timestamp = recastRequestDTO.getNlp().getTimestamp().substring(0,10);

        List<MoneyDTO> money = Optional.ofNullable(recastRequestDTO.getNlp().getEntities().getMoney())
                .orElseGet(Collections::emptyList);

        Double initialSum = Optional.ofNullable(expense.getValue()).orElse(0.0);

        Double sum = money.stream().map(MoneyDTO::getAmount).reduce(initialSum, Double::sum);


        final Function<MoneyDTO, ExpenseItem> moneyToItem = moneyDTO ->
                GenericBuilder.of(ExpenseItem::new)
                        .with(ExpenseItem::setDate, timestamp)
                        .with(ExpenseItem::setValue, moneyDTO.getAmount().toString() + " " + moneyDTO.getCurrency())
                        .with(ExpenseItem::setDoubleValue, moneyDTO.getAmount())
                        .build();

        List<ExpenseItem> expenseItems = money.stream().map(moneyToItem).collect(Collectors.toList());

        expenseItems.forEach(expenseItem -> expense.getItems().add(expenseItem));
        expense.setId(recastRequestDTO.getConversation().getId());
        expense.setValue(sum);
        expensesRepository.save(expense);
    }

    public RecastReply getExpenses(RecastRequestDTO recastRequestDTO){
        Expense expense = expensesRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseThrow(()-> EntityNotFoundException.withMessage("No expenses found"));

        List<DateTimeDTO> timestamp = Optional.ofNullable(recastRequestDTO.getNlp().getEntities().getDatetime())
                .orElse(Collections.emptyList());

        String month = timestamp.stream()
                .findFirst()
                .map(DateTimeDTO::getIso).map(s -> s.substring(5,7))
                .orElse(recastRequestDTO.getNlp().getTimestamp().substring(5,7));

        List<ExpenseItem> expenseItems = expense.getItems().stream()
                .filter(expenseItem ->month.equals(expenseItem.getDate().substring(5,7)))
                .collect(Collectors.toList());

        String items = expenseItems.stream()
                .map(expenseItem -> expenseItem.getValue() + " on " +expenseItem.getDate())
                .collect(Collectors.joining("\n"));

        String message = "Your Expenses for " + monthConvertor(month) + " is: "+ expense.getValue();
        if (expenseItems.isEmpty()){
            message = "No expenses found for " + monthConvertor(month);
        }

        RecastReply recastReply = new RecastReply(new ArrayList<>());
        TextMessage textMessage1 = new TextMessage(message);
        TextMessage textMessage = new TextMessage(items);
        recastReply.getReplies().add(textMessage1);
        recastReply.getReplies().add(textMessage);

        return  recastReply;
    }

    public RecastReply deleteExpense(RecastRequestDTO recastRequestDTO){
        Expense expense = expensesRepository.findById(recastRequestDTO.getConversation().getId())
                .orElseThrow(()-> EntityNotFoundException.withMessage("No expenses found"));
        String timestamp = recastRequestDTO.getNlp().getEntities().getDatetime().stream()
                .findFirst()
                .map(DateTimeDTO::getIso)
                .map(s -> s.substring(5,10))
                .orElse("");

        List<ExpenseItem> expenseItems = expense.getItems().stream()
                .filter(item ->needsRemoval(item,recastRequestDTO))
                .collect(Collectors.toList());
        String message = "Successful";
        if (expenseItems.isEmpty()){
            message = "Can't remove what doesn't exist";
        }
        Double value = expenseItems.stream()
                .map(ExpenseItem::getDoubleValue)
                .reduce(expense.getValue(),((aDouble, aDouble2) -> aDouble-aDouble2));

        expense.setValue(value);
        expense.getItems().removeIf(item ->needsRemoval(item,recastRequestDTO));
        expensesRepository.save(expense);

        RecastReply recastReply = new RecastReply(new ArrayList<>());
        TextMessage textMessage1 = new TextMessage(message);
        recastReply.getReplies().add(textMessage1);
        return recastReply;

    }

    private Boolean needsRemoval(ExpenseItem expenseItem, RecastRequestDTO recastRequestDTO){
        String timestamp = recastRequestDTO.getNlp().getEntities().getDatetime().stream()
                .findFirst()
                .map(DateTimeDTO::getIso)
                .map(s -> s.substring(5,10))
                .orElse("");
        List<MoneyDTO> moneyDTOS = Optional.ofNullable(recastRequestDTO.getNlp().getEntities().getMoney())
                .orElse(Collections.emptyList());
        Double value = moneyDTOS.stream().findFirst().map(MoneyDTO::getAmount).orElse(0.0);
        if (timestamp.equals(expenseItem.getDate().substring(5,10)) && value.equals(0.0)){
            return true;
        }else
            return timestamp.equals(expenseItem.getDate().substring(5, 10)) && value.equals(expenseItem.getDoubleValue());
    }

    private String monthConvertor(String month){
        switch (month){
            case "01":
                return "January ";
            case "02":
                return "February ";
            case "03":
                return "March ";
            case "04":
                return "April ";
            case "05":
                return "May ";
            case "06":
                return "June ";
            case "07":
                return "July ";
            case "08":
                return "August ";
            case "09":
                return "September ";
            case "10":
                return "Octomber ";
            case "11":
                return "November ";
            case "12":
                return "December ";
            default:
                return "";
        }

    }
}
