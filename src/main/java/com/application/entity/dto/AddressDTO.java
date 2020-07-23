package com.application.entity.dto;

import com.application.entity.Province;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class AddressDTO {

    private int addressId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private int provinceId;
    private String province;
    private String postalCode;
    private String country;
    private String deliveryNotes;
    private int userId;

    public AddressDTO() {
    }

    public AddressDTO(int addressId, String addressLine1, String addressLine2, String city,
                      int provinceId, String province, String postalCode, String country, String deliveryNotes, int userId) {
        this.addressId = addressId;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.provinceId = provinceId;
        this.province = province;
        this.postalCode = postalCode;
        this.country = country;
        this.deliveryNotes = deliveryNotes;
        this.userId = userId;
    }
}
