package com.application.repository;

import com.application.entity.Province;

import java.util.Collection;

public interface IProvinceRepo {

    Collection<Province> getAllProvinces();
    Collection<Province> findByString(String provinceToFind);
    Province findById(int id);
}
