package com.application.service;

import com.application.exception.ServiceException;
import com.application.repository.IDishRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
class DishServiceImplTest {
    @MockBean
    private IDishRepo dishRepoMock;

    @Autowired
    ApplicationContext context;

    //    private IDishRepo dishRepoMock;
//    private IDishStatusService dishStatusServiceMock;
//    private IMenuItemService menuItemServiceMock;
//    private IPriceService priceServiceMock;
    @MockBean
    private IDishService testClass;
//    private Order orderMock;
//    private PassedEntitiesValidator passedEntitiesValidator;

    @BeforeEach
    void setUp() {
//        dishRepoMock = mock(IDishRepo.class);
//        dishStatusServiceMock = mock(IDishStatusService.class);
//        menuItemServiceMock = mock(IMenuItemService.class);
//        priceServiceMock = mock(IPriceService.class);
//        testClass = new DishServiceImpl(dishRepoMock, dishStatusServiceMock, menuItemServiceMock, priceServiceMock);
//        orderMock = mock(Order.class);
//        passedEntitiesValidator = mock(PassedEntitiesValidator.class);

    }

    @Test
    void addDishes() {

        assertThrows(ServiceException.class, () -> testClass.addDishes(null));

    }

    @Test
    void getAllDishes() {
    }

    @Test
    void pushDishStatusFurther() {
    }
}