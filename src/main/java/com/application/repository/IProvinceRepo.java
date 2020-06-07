package com.application.repository;

import com.application.entity.Province;
import com.application.exception.RepoException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProvinceRepo extends JpaRepository<Province,Integer> {

    Province getByProvinceId(int id);

    List<Province> getAllProvinces();

    Province getByFullNameProvince(String provinceToFind);

    List<Province> getByFullNameProvinceContains(String provinceToFind);


}
