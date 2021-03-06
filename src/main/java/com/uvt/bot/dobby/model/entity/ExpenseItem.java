package com.uvt.bot.dobby.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class ExpenseItem {

    @Id
    @GeneratedValue
    private Long itemsId;

    private String value;
    private String date;
    private Double doubleValue;
}
