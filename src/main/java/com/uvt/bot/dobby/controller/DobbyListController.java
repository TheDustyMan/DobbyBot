package com.uvt.bot.dobby.controller;

import com.uvt.bot.dobby.model.entity.DobbyList;
import com.uvt.bot.dobby.model.entity.DobbyListItem;
import com.uvt.bot.dobby.repository.DobbyListRepository;
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
    private DobbyListRepository dobbyListRepository;

    @Autowired
    DobbyListController(DobbyListRepository dobbyListRepository) {
        this.dobbyListRepository = dobbyListRepository;
    }

    @GetMapping("/getDobbyList/{id}")
    public Optional<DobbyList> getDobbyList(@PathVariable String id) {
        return dobbyListRepository.findById(id);
    }

    @GetMapping("/getDobbyList")
    public Iterable<DobbyList> getAllDobbyLists() {
        return dobbyListRepository.findAll();
    }

    @PostMapping("/createDobbyList")
    public DobbyList createNewDobbyList(@RequestBody DobbyList dobbyList) {
        return dobbyListRepository.save(dobbyList);
    }

    @PutMapping("/updateDobbyList/{id}")
    public DobbyList updateDobbyList(@RequestBody DobbyList dobbyList, @PathVariable String id) {
        Optional<DobbyList> list = dobbyListRepository.findById(id);
        list.get()
                .getItems()
                .addAll(dobbyList.getItems());
        return dobbyListRepository.save(list.get());

    }

    @DeleteMapping("/deleteDobbyList/{id}")
    public String deleteDobbyList(@PathVariable String id){
        Optional<DobbyList> list = dobbyListRepository.findById(id);
        if (list.isPresent()) {
            dobbyListRepository.delete(list.get());
            return "SUCCESS";
        }
        else return "FAIL";
    }
}
