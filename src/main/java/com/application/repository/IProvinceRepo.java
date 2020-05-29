package com.application.repository;

import com.application.entity.Province;
import com.application.exception.RepoException;

import java.util.List;

public interface IProvinceRepo {

    void add(Province province) throws RepoException;

    Province getById(int id);

    List<Province> getAllProvinces();

    Province getByProvinceName(String provinceToFind) throws RepoException;

    List<Province> getByNameLike(String provinceToFind);

    boolean updateName(Province oldProvince, Province newProvince) throws RepoException;

    //TODO - REMOVE PROVINCE
}
