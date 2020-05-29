package com.application;

import com.application.entity.Province;
import com.application.entity.User;
import com.application.entity.UserRole;
import com.application.exception.ServiceException;
import com.application.service.IProvinceService;
import com.application.service.IUserRoleService;
import com.application.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        IProvinceService provinceService = applicationContext.getBean(IProvinceService.class);
        IUserService userService = applicationContext.getBean(IUserService.class);
        IUserRoleService userRoleService = applicationContext.getBean(IUserRoleService.class);
        UserRole userRole = new UserRole();
        userRole.setRoleName("Manager");
        UserRole userRole2 = new UserRole();
        userRole2.setRoleName("PositionDoesntExist");
        try {
            userRoleService.add(userRole);
//            userRoleService.remove(userRole);
        } catch (ServiceException e) {
            System.out.println("EXCEPTION!");
        }
        System.out.println(provinceService.findById(1));

        List<User> userList = (ArrayList) userService.getAll();
        if (userList != null) {
            userList.forEach(m -> System.out.println(m.toString()));
        }
        Province province = new Province();
        province.setFullNameProvince("Ontario");
        province.setAbbreviationProvince("ON");
        try {
            provinceService.add(province);

        } catch (ServiceException e) {
            System.out.println("EXCEPTION!");
        }
        Province province2 = new Province();
        province2.setFullNameProvince("Ont");
        List<Province> provincesList = (ArrayList) provinceService.findByProvinceName(province);
        if (provincesList != null) {
            provincesList.forEach(m -> System.out.println(m.toString()));
        }
        System.out.println(provinceService.updateName(province, null));

    }
}
