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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
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

    @Mock
    private List<Dish> dishList = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        dishList.add(any(Dish.class));
    }

    @Test
    public void getDishesTestBasic() throws Exception {
        this.mockMvc.perform(
                get("/dishes"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void getDishesTesWithStationAndStatusBothValid() throws Exception {

        this.mockMvc.perform(
                get("/dishes").param("stationType", "bar").param("status", "pending"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void pushDishStatus() {
    }
}
