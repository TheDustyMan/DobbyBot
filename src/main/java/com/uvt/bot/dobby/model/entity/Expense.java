package com.uvt.bot.dobby.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "EXPENSE")
public class Expense {

    @Id
    private String id;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "EXPENSE_ID")
    private List<ExpenseItem> items ;
}
