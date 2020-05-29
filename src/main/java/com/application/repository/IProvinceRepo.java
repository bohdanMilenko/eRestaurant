package com.application.repository;

import com.application.entity.Province;
import com.application.exception.RepoException;

import java.util.Collection;

public interface IProvinceRepo {

    void add(Province province) throws RepoException;

    Collection<Province> getAllProvinces();

    Province findByString(String provinceToFind) throws RepoException;

    Collection<Province> findByNameLike(String provinceToFind);

    Province findById(int id);

    boolean updateName(Province oldProvince, Province newProvince) throws RepoException;
}
