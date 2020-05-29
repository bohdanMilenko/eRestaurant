package com.application.service;

import com.application.entity.Province;
import com.application.exception.ServiceException;

import java.util.List;

public interface IProvinceService {

    void add(Province province) throws ServiceException;
    //TODO - CHANGE RETURN TYPE TO LIST
    List<Province> getAllProvinces();
    //TODO - CHANGE RETURN TYPE TO PROVINCE
    Province findByProvinceName(Province provinceToFind) throws ServiceException;
    Province findById(int id);
    List<Province> findByNameLike(Province provinceToFind);
    boolean updateName(Province oldProvince, Province newProvince);
}
