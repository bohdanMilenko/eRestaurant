package com.application;

import com.application.entity.User;
import com.application.repository.IProvinceRepo;
import com.application.repository.IUserRepo;
import com.application.service.IProvinceService;
import com.application.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        IProvinceService provinceService = applicationContext.getBean(IProvinceService.class);
        IUserService userService = applicationContext.getBean(IUserService.class);

        System.out.println(provinceService.findById(1));

        List<User> userList = (ArrayList) userService.getAll();
        if(userList!=null) {
            userList.forEach(m -> System.out.println(m.toString()));
        }
    }
}
