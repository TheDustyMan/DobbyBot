package com.uvt.bot.dobby.model.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "DOBBY_LIST")
public class DobbyList {

    @Id
    private String id;

    private String name;

    private String date;

    @OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "DOBBY_LIST_ID")
    private List<DobbyListItem> items ;

    protected DobbyList() {}

    public DobbyList(String date){
        this.date = date;
        items = new ArrayList<>() ;
    }

    public DobbyList(String name, String date, List<DobbyListItem> items){
        this.name = name;
        this.items = items;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<DobbyListItem> getItems() {
        return items;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setItems(List<DobbyListItem> items) {
        this.items = items;
    }
}
