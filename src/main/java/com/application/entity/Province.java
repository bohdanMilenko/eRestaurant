package com.application.entity;

import javax.persistence.*;

@Entity
@Table(name = "province")
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

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getAbbreviationProvince() {
        return abbreviationProvince;
    }

    public void setAbbreviationProvince(String abbreviationProvince) {
        this.abbreviationProvince = abbreviationProvince;
    }

    public String getFullNameProvince() {
        return fullNameProvince;
    }

    public void setFullNameProvince(String fullNameProvince) {
        this.fullNameProvince = fullNameProvince;
    }

    @Override
    public String toString() {
        return fullNameProvince;
    }
}
