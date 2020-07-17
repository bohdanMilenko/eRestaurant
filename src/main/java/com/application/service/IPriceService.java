package com.application.service;

import com.application.entity.MenuItem;
import com.application.entity.Price;

public interface IPriceService {

    Price getPriceByMenuItem(MenuItem menuItem);
}
