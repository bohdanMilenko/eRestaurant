package com.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "dish_status")
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

    public int getDishStatusId() {
        return dishStatusId;
    }

    public void setDishStatusId(int dishStatusId) {
        this.dishStatusId = dishStatusId;
    }

    public String getDishStatusName() {
        return dishStatusName;
    }

    public void setDishStatusName(String dishStatusName) {
        this.dishStatusName = dishStatusName;
    }

    @Override
    public String toString() {
        return "DishStatus{" +
                "dishStatusId=" + dishStatusId +
                ", dishStatusName=" + dishStatusName +
                '}';
    }
}
