package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.entity.DobbyReminder;
import com.uvt.bot.dobby.repository.DobbyReminderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class ReminderController {

    private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);
    private DobbyReminderRepository dobbyReminderRepository;

    @Autowired
    ReminderController(DobbyReminderRepository dobbyReminderRepository){
        this.dobbyReminderRepository = dobbyReminderRepository;
    }

    @GetMapping("/getReminder")
    public List<DobbyReminder> getReminder(@RequestBody DobbyReminder dobbyReminder){
        return (List<DobbyReminder>) dobbyReminderRepository.findAll();

    }

    @PostMapping("/createReminder")
    public DobbyReminder createReminder(@RequestBody DobbyReminder dobbyReminder){
       return dobbyReminderRepository.save(dobbyReminder);
    }

    @DeleteMapping("/deleteReminder")
    public void deleteReminder(@RequestBody DobbyReminder dobbyReminder){
        Optional<DobbyReminder> reminderToDelete = dobbyReminderRepository.findById(dobbyReminder.getId());
        dobbyReminderRepository.delete(reminderToDelete.get());
    }
}
