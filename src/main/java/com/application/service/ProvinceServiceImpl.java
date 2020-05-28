package com.application.service;

import com.application.entity.Province;
import com.application.exception.RepoException;
import com.application.repository.IProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        return provinceRepo.getAllProvinces();
    }

    @Override
    public Collection<Province> findByProvinceName(Province provinceToFind) {
        if (provinceToFind != null && provinceToFind.getFullNameProvince() != null){
            return provinceRepo.findByNameLike(provinceToFind.getFullNameProvince());
        }
        return null;
    }

    @Override
    public Province findById(int id) {
        return provinceRepo.findById(id);
    }

    @Override
    public Collection<Province> findByNameLike(Province provinceToFind) {
        if (provinceToFind != null && provinceToFind.getFullNameProvince() != null){
            return provinceRepo.findByNameLike(provinceToFind.getFullNameProvince());
        }
        return null;
    }

    @Override
    @Transactional
    public boolean updateName(Province oldProvince, Province newProvince) {
        if (oldProvince != null && newProvince != null) {
            if (oldProvince.getFullNameProvince() != null && newProvince.getFullNameProvince() != null) {
                try {
                    return provinceRepo.updateName(oldProvince, newProvince);
                }catch (RepoException e){
                    return false;
                }

            }
        }
        return false;
    }
}
