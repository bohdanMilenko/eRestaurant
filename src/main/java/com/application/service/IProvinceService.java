package com.application.service;

import com.application.entity.Province;
import com.application.exception.ServiceException;

import java.util.Collection;

public interface IProvinceService {

    void add(Province province) throws ServiceException;
    Collection<Province> getAllProvinces();
    Collection<Province> findByProvinceName(Province provinceToFind);
    Province findById(int id);
    Collection<Province> findByNameLike(Province provinceToFind);
    boolean updateName(Province oldProvince, Province newProvince);
}
