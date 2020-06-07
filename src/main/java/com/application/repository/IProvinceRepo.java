package com.application.repository;

import com.application.entity.Province;
import com.application.exception.RepoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface IProvinceRepo extends JpaRepository<Province,Integer> {

    Province getByProvinceId(int id);

    Province findByFullNameProvinceOrAbbreviationProvince(String provinceToFind, String abbreviation);

    List<Province> getByFullNameProvinceContains(String provinceToFind);


}
