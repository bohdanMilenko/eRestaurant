package com.application.repository;

import com.application.entity.Province;
import com.application.exception.NoSuchEntityException;
import com.application.exception.RepoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class ProvinceRepoImpl implements IProvinceRepo {

    @PersistenceContext
    EntityManager em;

    private final static Logger logger = LoggerFactory.getLogger(ProvinceRepoImpl.class);

    public ProvinceRepoImpl() {
    }

    @Override
    public Collection<Province> getAllProvinces() {
        TypedQuery<Province> query = em.createQuery("SELECT p FROM Province p", Province.class);
        return query.getResultList();
    }

    @Override
    public Province findByString(String provinceToFind) throws RepoException {
        TypedQuery<Province> query = em.createQuery("SELECT p FROM Province p WHERE p.fullNameProvince =:province", Province.class)
                .setParameter("province", provinceToFind);
        try{
            return query.getSingleResult();
        }catch (NoResultException e){
            logger.debug("Unable to find such record in table Province:" + provinceToFind, e);
            throw new NoSuchEntityException("Province with name " + provinceToFind + " is not found", e);
        }
    }

    @Override
    public Collection<Province> findByNameLike(String provinceToFind) {
        TypedQuery<Province> query = em.createQuery("SELECT p FROM Province p WHERE p.fullNameProvince LIKE :province " +
                "ORDER BY p.fullNameProvince", Province.class)
                .setParameter("province", provinceToFind + "%");
        logger.info("Getting Province by name: " + provinceToFind);
        return query.getResultList();
    }


    @Override
    public Province findById(int id) {
        return em.find(Province.class, id);
    }

    @Override
    public boolean updateName(Province oldProvince, Province newProvince) throws RepoException {
        if (oldProvince != null && newProvince != null) {
            logger.info("Updating province name from " + oldProvince.getFullNameProvince() +
                    " to " + newProvince.getFullNameProvince());
            if (oldProvince.getFullNameProvince() != null && newProvince.getFullNameProvince() != null) {

                Province province = findByString(oldProvince.getFullNameProvince());
                province.setFullNameProvince(newProvince.getFullNameProvince());
                return true;
            }
        }
        return false;
    }
}
