package com.application;

import com.application.repository.IProvinceRepo;
import com.application.service.IProvinceService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        IProvinceService provinceService = applicationContext.getBean(IProvinceService.class);

        provinceService.findById(1);
    }
}
