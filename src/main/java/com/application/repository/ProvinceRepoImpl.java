package com.application.repository;

import com.application.entity.Province;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class ProvinceRepoImpl implements IProvinceRepo {

    @PersistenceContext
    EntityManager em;

    public ProvinceRepoImpl() {
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
        return em.find(Province.class, id);
    }
}
