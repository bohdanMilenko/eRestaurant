package com.application;

import com.application.entity.MenuCategory;
import com.application.entity.Province;
import com.application.entity.UserRole;
import com.application.exception.ServiceException;
import com.application.service.IMenuCategoryService;
import com.application.service.IProvinceService;
import com.application.service.IUserRoleService;
import com.application.service.IUserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        IProvinceService provinceService = applicationContext.getBean(IProvinceService.class);
        IUserService userService = applicationContext.getBean(IUserService.class);
        IUserRoleService userRoleService = applicationContext.getBean(IUserRoleService.class);
        IMenuCategoryService menuCategoryService = applicationContext.getBean(IMenuCategoryService.class);

        MenuCategory menuCategoryAppetizer = new MenuCategory("Appetizer");
        try {
            menuCategoryService.addMenuCategory(menuCategoryAppetizer);
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        System.out.println("\tUSER ROLES!!!");
        UserRole userRole = new UserRole();
        userRole.setRoleName("Manager");
        UserRole userRole2 = new UserRole();
        userRole2.setRoleName("PositionDoesntExist");
//        try {
//            userRoleService.add(userRole);
////            userRoleService.remove(userRole);
//        } catch (ServiceException e) {
//            System.out.println("EXCEPTION!");
//        }
        System.out.println(provinceService.getById(1));


        System.out.println("\tPROVINCES!!!");
        Province province = new Province();
        province.setFullNameProvince("Ontario");
        province.setAbbreviationProvince("ON");
        Province province2 = new Province();
        province2.setFullNameProvince("N");
        province2.setAbbreviationProvince("N");
        Province albertaProvince = new Province();
        albertaProvince.setFullNameProvince("Alb");
        Province novaScotiaProvince = new Province();
        novaScotiaProvince.setFullNameProvince("Nova Scotia");
        novaScotiaProvince.setAbbreviationProvince("NS");
        Province newBrunswickProvince = new Province();
        newBrunswickProvince.setFullNameProvince("New Brunswick");
        newBrunswickProvince.setAbbreviationProvince("NB");
        try {
            provinceService.add(province);

        } catch (ServiceException e) {
            System.out.println("EXCEPTION ADDING A PROVINCE!");
        }
        System.out.println("PRINT ALL:");
        provinceService.getAllProvinces().forEach(m -> System.out.println(m.toString()));
        System.out.println("FIND BY NAME LIKE N");
        try {
            provinceService.getByFullProvinceNameContains(province2).forEach(m -> System.out.println(m.toString()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("FIND BY NAME LIKE ALB");
            provinceService.getByFullProvinceNameContains(albertaProvince).forEach(m -> System.out.println(m.toString()));
            System.out.println("TRYING PROVINCE METHODS");
            provinceService.add(novaScotiaProvince);
            provinceService.add(newBrunswickProvince);
        } catch (ServiceException e) {
            System.out.println("EXCEPTION!");
        }
        try {
            System.out.println("find by province name NS");
            System.out.println(provinceService.getByProvinceName(novaScotiaProvince).toString());

        } catch (ServiceException e) {
            System.out.println("EXCEPTION!");
        }


//        System.out.println(provinceService.updateName(province, null));

    }

}
