package com.application.service;

import com.application.entity.Province;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.IProvinceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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
    @Transactional
    public void add(Province province) throws ServiceException {
        try {
            provinceRepo.add(province);
        }catch (RepoException e){
            throw new ServiceException("Repo failed to add new Province: " + province.toString(), e);
        }

    }

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepo.getAllProvinces();
    }

    @Override
    @Transactional
    public Province findByProvinceName(Province provinceToFind) throws ServiceException {
        if (provinceToFind != null && provinceToFind.getFullNameProvince() != null){
            try {
                return provinceRepo.getByProvinceName(provinceToFind.getFullNameProvince());
            }catch (RepoException e){
                throw new ServiceException("Repo failed to find one province", e);
            }
        }
        return null;
    }

    @Override
    public Province findById(int id) {
        return provinceRepo.getById(id);
    }

    @Override
    @Transactional
    public List<Province> findByNameLike(Province provinceToFind) {
        if (provinceToFind != null && provinceToFind.getFullNameProvince() != null){
            return provinceRepo.getByNameLike(provinceToFind.getFullNameProvince());
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
