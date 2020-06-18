package com.application;

import com.application.entity.*;
import com.application.exception.ServiceException;
import com.application.service.*;
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
        IMenuCategoryService menuCategoryService = applicationContext.getBean(IMenuCategoryService.class);
        IOrderService orderService = applicationContext.getBean(IOrderService.class);

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

        System.out.println("\n\tTESTING ORDER!!!");
        List<Dish> dishList = new ArrayList<>();
        Dish firstDish = new Dish();
        Dish secondDish = new Dish();

        dishList.add(firstDish);
        dishList.add(secondDish);

        User user = new User();
        user.setUserId(1);
        user.setEmail("dowJones@gmail.com");
        user.setPhoneNumber("999888777");

        PaymentMethod paymentMethod = new PaymentMethod();
        paymentMethod.setPaymentMethodId(1);

        Address address = new Address();
        address.setAddressId(1);


        Order order = new Order();
        order.setUser(user);
        order.setOrderedDishes(dishList);
        order.setTotalAmount(500);
        order.setPaymentMethod(paymentMethod);
        order.setAddress(address);

        try{
            orderService.addOrder(order);
        } catch (ServiceException e) {
            System.out.println("Exception in adding order! " + e.toString());
        }


    }

}
