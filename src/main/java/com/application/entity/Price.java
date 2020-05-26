package com.application.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private int priceId;
    @Column(name = "start_time")
    private LocalDateTime priceStartDateTime;
    @Column(name = "price")
    private int priceValue;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuRestaurantItem menuRestaurantItem;

    public Price() {
    }

    public Price(int priceValue, MenuRestaurantItem menuRestaurantItem) {
        this.priceValue = priceValue;
        this.menuRestaurantItem = menuRestaurantItem;
    }

    public int getPriceId() {
        return priceId;
    }

    public void setPriceId(int priceId) {
        this.priceId = priceId;
    }

    public LocalDateTime getPriceStartDateTime() {
        return priceStartDateTime;
    }

    public void setPriceStartDateTime(LocalDateTime priceStartDateTime) {
        this.priceStartDateTime = priceStartDateTime;
    }

    public int getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(int priceValue) {
        this.priceValue = priceValue;
    }

    public MenuRestaurantItem getMenuRestaurantItem() {
        return menuRestaurantItem;
    }

    public void setMenuRestaurantItem(MenuRestaurantItem menuRestaurantItem) {
        this.menuRestaurantItem = menuRestaurantItem;
    }

    @Override
    public String toString() {
        return "Price{" +
                "priceId=" + priceId +
                ", priceStartDateTime=" + priceStartDateTime +
                ", priceValue=" + priceValue +
                ", menuRestaurantItem=" + menuRestaurantItem +
                '}';
    }
}
