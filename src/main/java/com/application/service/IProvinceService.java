package com.application.service;

import com.application.entity.Province;

import java.util.Collection;

public interface IProvinceService {

    Collection<Province> getAllProvinces();
    Collection<Province> findByString(String provinceToFind);
    Province findById(int id);
}
