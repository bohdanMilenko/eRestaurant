package com.application.service;

import com.application.entity.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProvinceServiceImpl implements IProvinceService {

    IProvinceService provinceService;

    public ProvinceServiceImpl() {
    }

    @Autowired
    public ProvinceServiceImpl(IProvinceService provinceService) {
        this.provinceService = provinceService;
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
        return provinceService.findById(id);
    }
}
