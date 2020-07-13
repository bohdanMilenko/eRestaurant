package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "card_network_type")
@Data
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

}
