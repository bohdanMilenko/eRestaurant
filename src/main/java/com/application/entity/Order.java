package com.application.entity;

import com.application.util.DateConverter;
import com.application.util.jsonSerializer.OrderSerializer;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@JsonSerialize(using = OrderSerializer.class)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "total_amount")
    private int totalAmount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "order")
    @JsonManagedReference
    @ToString.Exclude
    private List<Dish> orderedDishes;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus orderStatus;

    @Column(name = "ordered_time")
    @Convert(converter = DateConverter.class)
    private LocalDateTime orderedTime;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;


    public Order() {
    }

    public Order(int totalAmount, User user, List<Dish> orderedDishes,
                 OrderStatus orderStatus, LocalDateTime orderedTime, PaymentMethod paymentMethod, Address address) {
        this.totalAmount = totalAmount;
        this.user = user;
        this.orderedDishes = orderedDishes;
        this.orderStatus = orderStatus;
        this.orderedTime = orderedTime;
        this.paymentMethod = paymentMethod;
        this.address = address;
    }

}
