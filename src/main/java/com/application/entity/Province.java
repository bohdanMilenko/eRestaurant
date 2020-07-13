package com.application.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "province")
@Data
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private int provinceId;
    @Column(name = "province_abbr")
    private String abbreviationProvince;
    @Column(name = "province_full_name")
    private String fullNameProvince;

    public Province() {
    }

    public Province(int provinceId, String abbreviationProvince, String fullNameProvince) {
        this.provinceId = provinceId;
        this.abbreviationProvince = abbreviationProvince;
        this.fullNameProvince = fullNameProvince;
    }

}
