package com.application.service;

import com.application.entity.Province;
import com.application.repository.IProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProvinceServiceImpl implements IProvinceService {


    IProvinceRepo provinceRepo;

    public ProvinceServiceImpl() {
    }

    @Autowired
    public ProvinceServiceImpl(IProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    @Override
    public Collection<Province> getAllProvinces() {
        return null;
    }

    @Override
    public Collection<Province> findByString(String provinceToFind) {
        return null;
    }

    @Override
    public Province findById(int id) {
        return provinceRepo.findById(id);
    }
}
