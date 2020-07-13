package com.application.util.dtoEntityConverter;

import com.application.entity.Dish;
import com.application.entity.dto.DishDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DishConverter {

    private static ModelMapper modelMapper;

    public DishConverter() {
    }

    @Autowired
    public DishConverter(ModelMapper modelMapper) {
        DishConverter.modelMapper = modelMapper;
    }


    public static List<DishDTO> convertToDto(List<Dish> dishList) {
        return dishList.stream()
                .map(dish -> modelMapper.map(dish, DishDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Dish> convertToEntity(List<DishDTO> dishDTOList) {

        return dishDTOList.stream()
                .map(dish -> modelMapper.map(dish, Dish.class))
                .collect(Collectors.toList());
    }
}
