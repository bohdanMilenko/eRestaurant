package com.application.repository;

import com.application.entity.Province;
import com.application.exception.RepoValidationFailedException;

import java.util.Collection;

public interface IProvinceRepo {

    Collection<Province> getAllProvinces();

    Province findByString(String provinceToFind) throws RepoValidationFailedException;

    Collection<Province> findByNameLike(String provinceToFind);

    Province findById(int id);

    boolean updateName(Province oldProvince, Province newProvince) throws RepoValidationFailedException;
}
