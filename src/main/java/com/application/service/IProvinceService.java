package com.application.service;

import com.application.entity.Province;
import com.application.exception.ServiceException;

import java.util.List;

public interface IProvinceService {

    void add(Province province) throws ServiceException;

    List<Province> getAllProvinces();

    Province getByProvinceName(Province provinceToFind) throws ServiceException;

    Province getById(int id);

    List<Province> getByFullProvinceNameContains(Province provinceToFind) throws ServiceException;

    Province updateName(Province oldProvince, Province newProvince) throws ServiceException;

}
