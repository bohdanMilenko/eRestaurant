package com.application.util.dtoEntityConverter;

import com.application.entity.Dish;
import com.application.entity.MenuItem;
import com.application.entity.dto.DishDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
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
        modelMapper.addMappings(dishToDTOMapping);
//        modelMapper.addMappings(DTOToDishMapping);
    }

    public static DishDTO convertToDto(Dish dish) {
        return modelMapper.map(dish, DishDTO.class);
    }

    public static Dish convertToEntity(DishDTO dishDTO) {
        return modelMapper.map(dishDTO, Dish.class);
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

    PropertyMap<Dish, DishDTO> dishToDTOMapping = new PropertyMap<Dish, DishDTO>() {
        protected void configure() {
            map().setDishId(source.getDishId());
            map().setMenuItemId(source.getMenuItem().getMenuItemId());
            map().setMenuItemName(source.getMenuItem().getMenuItemName());
            map().setDishStatus(source.getDishStatus().getDishStatusName());
            map().setImageLink(source.getMenuItem().getImageLink());
            map().setOrderedQuantity(source.getQuantityOrdered());
            map().setPrice(source.getPrice().getPriceValue());
            map().setOrderedDateTime(source.getOrder().getOrderedTime());
        }
    };

//    PropertyMap<DishDTO, Dish> DTOToDishMapping = new PropertyMap<>() {
//        protected void configure() {
//            map().setDishId(map().getDishId());
//            map().setMenuItem(new MenuItem(source.getMenuItemId(), source.getMenuItemName()));
//            map().setQuantityOrdered(source.getOrderedQuantity());
//        }
//    };
}
