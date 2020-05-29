package com.application.repository;

import com.application.entity.Province;
import com.application.exception.EntityValidationException;
import com.application.exception.RepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

import static com.application.util.PassedEntitiesValidator.validateObjectsForNull;
import static com.application.util.PassedEntitiesValidator.validateProvinceFieldsForNulls;

@Repository
public class ProvinceRepoImpl implements IProvinceRepo {

    @PersistenceContext
    EntityManager em;

    private final static Logger logger = LoggerFactory.getLogger(ProvinceRepoImpl.class);

    public ProvinceRepoImpl() {
    }

    @Override
    public void add(Province province) throws RepoException {
        try {
            validateObjectsForNull(province);
            logger.info("Starting writing to DB by using add(province = {})", province);
            validateProvinceFieldsForNulls(province);
            em.persist(province);
            logger.info("{} was successfully persisted by using add(province = (province))", province.toString());
        } catch (EntityValidationException e) {
            logger.debug("Object failed validation for add(province = {}))", province);
            throw new RepoException("Validation for Province failed: " + province.toString(), e);
        } catch (PersistenceException e) {
            logger.debug("{} violated table constraints and caused: {}", province, e.toString());
            throw new RepoException("{} failed to comply with the database constrains: " + province.toString()
                    + " .Cause " + e.getCause(), e);
        }
    }

    @Override
    public List<Province> getAllProvinces() {
        TypedQuery<Province> query = em.createQuery("SELECT p FROM Province p", Province.class);
        return query.getResultList();
    }

    @Override
    public Province getByProvinceName(String provinceToFind) throws RepoException {
        logger.info("Entering findByString( provinceToFind = {}", provinceToFind);
        TypedQuery<Province> query = em.createQuery("SELECT p FROM Province p WHERE p.fullNameProvince =:province", Province.class)
                .setParameter("province", provinceToFind);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            logger.debug("Unable to find such record in table Province {}, cause: {}", provinceToFind, e.toString());
            throw new RepoException("Province with name " + provinceToFind + " is not found", e);
        } catch (NonUniqueResultException e) {
            logger.debug("Multiple results found for passed : {}, cause: {}", provinceToFind, e.toString());
            throw new RepoException("Multiple results found or passed : " + provinceToFind, e);
        }
    }

    @Override
    public List<Province> getByNameLike(String provinceToFind) {
        logger.info("Entering findByNameLike(provinceToFind = {})", provinceToFind);
        TypedQuery<Province> query = em.createQuery("SELECT p FROM Province p WHERE p.fullNameProvince LIKE :province " +
                "ORDER BY p.fullNameProvince", Province.class)
                .setParameter("province", provinceToFind + "%");
        logger.info("Finished queering province by {}, size of returned collection {}", provinceToFind, query.getResultList().size());
        return query.getResultList();
    }


    @Override
    public Province getById(int id) {
        return em.find(Province.class, id);
    }

    @Override
    public boolean updateName(Province oldProvince, Province newProvince) throws RepoException {
        try {
            validateObjectsForNull(oldProvince, newProvince);
            validateProvinceFieldsForNulls(oldProvince, newProvince);
            logger.info("Entering updateName( old = {}, new = {} )", oldProvince, newProvince);
            validateProvinceFieldsForNulls(oldProvince, newProvince);
            Province province = getByProvinceName(oldProvince.getFullNameProvince());
            province.setFullNameProvince(newProvince.getFullNameProvince());
            logger.info("Successfully update provinces old = {}, new = {} )", oldProvince, newProvince);
            return true;
        } catch (EntityValidationException e) {
            logger.debug("Passed entities failed validation: updateName( old = {}, new ={})", oldProvince, newProvince);
            throw new RepoException(e);
        }
    }
}
