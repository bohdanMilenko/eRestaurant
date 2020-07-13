package com.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "address")
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private int addressId;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    @Column(name = "address_line")
    private String addressLine1;
    @Column(name = "address_line_2")
    private String addressLine2;
    @Column(name = "city")
    private String city;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private Province province;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "country")
    private String country;
    @Column(name = "delivery_notes")
    private String deliveryNotes;

    public Address() {
    }

    public Address(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public Address(int addressId, User user, String addressLine1, String addressLine2,
                   String city, Province province, String postalCode, String country, String deliveryNotes) {
        this.addressId = addressId;
        this.user = user;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.deliveryNotes = deliveryNotes;
    }


}
