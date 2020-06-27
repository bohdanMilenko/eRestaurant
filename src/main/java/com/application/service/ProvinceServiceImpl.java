package com.application.service;

import com.application.entity.Province;
import com.application.exception.EntityValidationException;
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

    private IProvinceRepo provinceRepo;
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
        logger.info("Starting writing to DB by using add(province = {})", province);
        try {
            validateObjectsForNull(province);
            validateProvinceFieldsForNulls(province);
            if (getByProvinceName(province) == null) {
                System.out.println(getByProvinceName(province));
                provinceRepo.save(province);
            } else {
                logger.error("Unable to execute add (province = {}), duplicated province", province);
                throw new ServiceException("Attempt to add duplicated province: " + province.getFullNameProvince());
            }
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for add(province = {}))", province);
            throw new ServiceException("Validation for (nulls) in Province failed: " + province, e);
        }
//        catch (RepoException e) {
//            logger.error("Repo threw exception while add( province = {}, and caused: {}", province, e.toString());
//            throw new ServiceException("Repo failed to add new Province: " + province.toString(), e);
//        }
    }

    @Override
    public List<Province> getAllProvinces() {
        return provinceRepo.findAll();
    }

    @Override
    public Province getByProvinceName(Province provinceToFind) throws ServiceException {
        logger.info("Entering findByString( provinceToFind = {}", provinceToFind);
        try {
            validateObjectsForNull(provinceRepo);
            validateProvinceFieldsForNulls(provinceToFind);
            return provinceRepo.findByFullNameProvinceOrAbbreviationProvince(provinceToFind.getFullNameProvince(), provinceToFind.getAbbreviationProvince());
//            return provinceRepo.getByFullNameProvince(provinceToFind.getFullNameProvince());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getByProvinceName(province = {}))", provinceToFind);
            throw new ServiceException("Validation for (nulls) in Province failed: " + provinceToFind, e);
        }
//        catch (RepoException e) {
//            logger.error("Unable to find getByProvinceName( province = {}), as it caused: {}", provinceToFind, e.toString());
//            throw new ServiceException("Repo failed to find one province", e);
//        }
    }

    @Override
    public Province getById(int id) {
        return provinceRepo.getByProvinceId(id);
    }

    @Override
    @Transactional
    public List<Province> getByFullProvinceNameContains(Province provinceToFind) throws ServiceException {
        logger.info("Entering findByNameLike(provinceToFind = {})", provinceToFind);
        try {
            validateObjectsForNull(provinceToFind);
            validateProvinceFieldsForNulls(provinceToFind);
            return provinceRepo.getByFullNameProvinceContains(provinceToFind.getFullNameProvince());
        } catch (EntityValidationException e) {
            logger.error("Object failed validation for getByNameLike(province = {}))", provinceToFind);
            throw new ServiceException("Validation for (nulls) in Province failed: " + provinceToFind, e);
        }
    }

    @Override
    @Transactional
    public Province updateName(Province oldProvince, Province updatedProvince) throws ServiceException {
        logger.info("Entering updateName( old = {}, new = {} )", oldProvince, updatedProvince);
        try {
            validateObjectsForNull(oldProvince, updatedProvince);
            validateProvinceFieldsForNulls(oldProvince, updatedProvince);
            Province fetchedProvince = provinceRepo.getOne(oldProvince.getProvinceId());
            fetchedProvince.setFullNameProvince(updatedProvince.getFullNameProvince());
            return provinceRepo.save(fetchedProvince);
        } catch (EntityValidationException e) {
            logger.error("Objects failed validation for updateName(oldProvince = {}, updatedProvince = {}))", oldProvince, updatedProvince);
            throw new ServiceException("Validation for (nulls) in Province failed", e);
        }
    }
}
