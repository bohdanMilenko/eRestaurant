package com.application.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private int paymentMethodId;
    @ManyToOne
    private User user;
    @Column(name = "payment_type")
    private String paymentType;
    @ManyToOne
    private CardNetworkType cardNetworkType;
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

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public CardNetworkType getCardNetworkType() {
        return cardNetworkType;
    }

    public void setCardNetworkType(CardNetworkType cardNetworkType) {
        this.cardNetworkType = cardNetworkType;
    }

    public boolean isCreditCard() {
        return isCreditCard;
    }

    public void setCreditCard(boolean creditCard) {
        isCreditCard = creditCard;
    }

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public String getCcv2() {
        return ccv2;
    }

    public void setCcv2(String ccv2) {
        this.ccv2 = ccv2;
    }

    public LocalDate getCcIssueDate() {
        return ccIssueDate;
    }

    public void setCcIssueDate(LocalDate ccIssueDate) {
        this.ccIssueDate = ccIssueDate;
    }

    public String getNameOnCard() {
        return nameOnCard;
    }

    public void setNameOnCard(String nameOnCard) {
        this.nameOnCard = nameOnCard;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentMethodId=" + paymentMethodId +
                ", user=" + user +
                ", paymentType='" + paymentType + '\'' +
                ", cardNetworkType=" + cardNetworkType +
                ", isCreditCard=" + isCreditCard +
                ", ccNumber='" + ccNumber + '\'' +
                ", ccv2='" + ccv2 + '\'' +
                ", ccIssueDate=" + ccIssueDate +
                ", nameOnCard='" + nameOnCard + '\'' +
                '}';
    }
}
