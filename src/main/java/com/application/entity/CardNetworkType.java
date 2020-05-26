package com.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "card_network_type")
public class CardNetworkType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "card_network_type_id")
    private int cardNetworkId;
    @Column(name = "card_network_name")
    private String cardProviderName;

    public CardNetworkType() {
    }

    public CardNetworkType(int cardNetworkId, String cardProviderName) {
        this.cardNetworkId = cardNetworkId;
        this.cardProviderName = cardProviderName;
    }

    public int getCardNetworkId() {
        return cardNetworkId;
    }

    public void setCardNetworkId(int cardNetworkId) {
        this.cardNetworkId = cardNetworkId;
    }

    public String getCardProviderName() {
        return cardProviderName;
    }

    public void setCardProviderName(String cardProviderName) {
        this.cardProviderName = cardProviderName;
    }

    @Override
    public String toString() {
        return "CardNetworkType{" +
                "cardNetworkId=" + cardNetworkId +
                ", cardProviderName='" + cardProviderName + '\'' +
                '}';
    }
}
