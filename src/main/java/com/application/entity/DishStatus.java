package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "dish_status")
@Data
public class DishStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dish_status_id")
    private int dishStatusId;
    @Column(name = "dish_status_name")
    private String dishStatusName;

    public DishStatus() {
    }

    public DishStatus(String dishStatusName) {
        this.dishStatusName = dishStatusName;
    }


}
