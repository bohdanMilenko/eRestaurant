package com.application.service;

import com.application.entity.MenuItem;
import com.application.entity.Price;
import com.application.repository.IPriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceServiceImpl implements IPriceService {

    private IPriceRepo priceRepo;


    @Autowired
    public PriceServiceImpl(IPriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    @Override
    public Price getPriceByMenuItem(MenuItem menuItem) {
        return priceRepo.getPriceByMenuItem(menuItem.getMenuItemId());
    }
}
