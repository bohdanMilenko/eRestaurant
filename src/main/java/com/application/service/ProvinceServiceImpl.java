package com.application.service;

import com.application.entity.Province;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import com.application.exception.ServiceException;
import com.application.repository.IProvinceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validateProvinceFieldsForNulls;

@Service
public class ProvinceServiceImpl implements IProvinceService {


    IProvinceRepo provinceRepo;
    private static final Logger logger = LoggerFactory.getLogger(ProvinceServiceImpl.class);

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
            logger.info("Starting writing to DB by using add(province = {})", province);
            validateObjectsForNull(province);
            validateProvinceFieldsForNulls(province);
            provinceRepo.add(province);
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for add(province = {}))", province);
            throw new ServiceException("Validation for (nulls) in Province failed: " + province.toString(), e);
        } catch (RepoException e) {
            logger.error("Repo threw exception while add( province = {}, and caused: {}", province, e.toString());
            throw new ServiceException("Repo failed to add new Province: " + province.toString(), e);
        }

    }

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepo.getAllProvinces();
    }

    @Override
    @Transactional
    public Province getByProvinceName(Province provinceToFind) throws ServiceException {
        try {
            logger.info("Entering findByString( provinceToFind = {}", provinceToFind);
            validateObjectsForNull(provinceRepo);
            validateProvinceFieldsForNulls(provinceToFind);
            return provinceRepo.getByProvinceName(provinceToFind.getFullNameProvince());
        } catch (RepoException e) {
            logger.error("Unable to find findByProvinceName( province = {}), as it caused: {}", provinceToFind, e.toString());
            throw new ServiceException("Repo failed to find one province", e);
        }
    }

    @Override
    public Province getById(int id) {
        return provinceRepo.getById(id);
    }

    @Override
    @Transactional
    public List<Province> getByNameLike(Province provinceToFind) throws ServiceException {
        logger.info("Entering findByNameLike(provinceToFind = {})", provinceToFind);
        try {
            validateObjectsForNull(provinceToFind);
            validateProvinceFieldsForNulls(provinceToFind);
            return provinceRepo.getByNameLike(provinceToFind.getFullNameProvince());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getByNameLike(province = {}))", provinceToFind);
            throw new ServiceException("Validation for (nulls) in Province failed: " + provinceToFind.toString(), e);
        }
    }

    @Override
    @Transactional
    public boolean updateName(Province oldProvince, Province newProvince) throws ServiceException {
        logger.info("Entering updateName( old = {}, new = {} )", oldProvince, newProvince);
        try {
            validateObjectsForNull(oldProvince, newProvince);
            validateProvinceFieldsForNulls(oldProvince, newProvince);
            return provinceRepo.updateName(oldProvince, newProvince);
        } catch (EntityValidationException e) {
            logger.error("Objects failed validation for updateName(oldProvince = {}, new Province = {}))", oldProvince, newProvince);
            throw new ServiceException("Validation for (nulls) in Province failed", e);
        }
    }
}
