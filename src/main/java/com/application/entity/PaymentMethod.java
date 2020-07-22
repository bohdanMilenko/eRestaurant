package com.application.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment_method")
@Data
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private int paymentMethodId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "card_network_type_id")
    private CardNetworkType cardNetworkType;

    @Column(name = "payment_type")
    private String paymentType;
    //Why is it tinyint?
    @Column(name = "is_cc")
    private boolean isCreditCard;
    @Column(name = "cc_number")
    private String ccNumber;
    @Column(name = "ccv2")
    private String ccv2;
    @Column(name = "issue_date")
    private LocalDate ccIssueDate;
    @Column(name = "name_on_card")
    private String nameOnCard;


    public PaymentMethod() {
    }

    public PaymentMethod(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }


    public PaymentMethod(int paymentMethodId, User user, String paymentType, CardNetworkType cardNetworkType, boolean isCreditCard,
                         String ccNumber, String ccv2, LocalDate ccIssueDate, String nameOnCard) {
        this.paymentMethodId = paymentMethodId;
        this.user = user;
        this.paymentType = paymentType;
        this.cardNetworkType = cardNetworkType;
        this.isCreditCard = isCreditCard;
        this.ccNumber = ccNumber;
        this.ccv2 = ccv2;
        this.ccIssueDate = ccIssueDate;
        this.nameOnCard = nameOnCard;
    }

}
