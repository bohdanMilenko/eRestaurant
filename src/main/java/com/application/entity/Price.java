package com.application.entity;

import com.application.util.DateConverter;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "price")
@Data
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "price_id")
    private int priceId;
    @Column(name = "start_time")
    @Convert(converter = DateConverter.class)
    private LocalDateTime priceStartDateTime;
    @Column(name = "price")
    private int priceValue;

    @ManyToOne
    @JoinColumn(name = "menu_item_id")
    private MenuItem menuItem;

    public Price() {
    }

    public Price(int priceValue, MenuItem menuItem) {
        this.priceValue = priceValue;
        this.menuItem = menuItem;
    }

}
