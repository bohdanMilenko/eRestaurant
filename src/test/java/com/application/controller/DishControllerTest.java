package com.application.controller;

import com.application.entity.Dish;
import com.application.service.IDishService;
import com.application.service.IOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebMvcTest(DishController.class)
public class DishControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    private IOrderService orderService;
    @MockBean
    private IDishService dishService;

    private List<Dish> dishListMock;
    private Dish dishMock;


    @BeforeEach
    public void setUp() {
        dishListMock = mock(ArrayList.class);
        dishMock = mock(Dish.class);
        dishListMock.add(dishMock);
    }

    @Test
    public void getDishesTestBasic() throws Exception {
        this.mockMvc.perform(
                get("/dishes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getDishesTestResponseType() throws Exception {
//        when(dishService.getDishesByTypeAndStatus(anyList(),anyString())).thenReturn(Arrays.asList(dishMock));
        MvcResult result = this.mockMvc.perform(
                get("/dishes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andReturn();

    }

//    @Test
//    public void getDishesTesWithStationAndStatusBothValid() throws Exception {
////        when(dishService.getDishesByTypeAndStatus(anyList(),anyString())).thenReturn(Arrays.asList(dishMock));
//        MvcResult result = this.mockMvc.perform(
//                get("/dishes").param("stationType", "bar").param("status", "pending"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.dishId").exists())
//                .andReturn();
//        assertEquals("application/json",
//                result.getResponse().getContentType());
//
//    }

    @Test
    public void pushDishStatus() {
    }
}
