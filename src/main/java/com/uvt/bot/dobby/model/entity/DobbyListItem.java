package com.uvt.bot.dobby.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class DobbyListItem implements Serializable{

    @Id
    @GeneratedValue
    private Long itemsId;

    private String item;

    public DobbyListItem() {}

    public DobbyListItem(String item){
        this.item = item;

    }
}
