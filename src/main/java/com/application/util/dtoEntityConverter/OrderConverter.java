package com.application.util.dtoEntityConverter;

import com.application.entity.Order;
import com.application.entity.dto.OrderDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {


    private static ModelMapper modelMapper;

    public OrderConverter() {
    }

    @Autowired
    public OrderConverter(ModelMapper modelMapper) {
        OrderConverter.modelMapper = modelMapper;
        modelMapper.addMappings(orderToDTOMapping);
        modelMapper.addMappings(DTOToOrderMapping);
    }

    PropertyMap<Order, OrderDTO> orderToDTOMapping = new PropertyMap<Order, OrderDTO>() {
        protected void configure() {
            map().setAddressLine(source.getAddress().getAddressLine1());
            map().setOrderedDateTime(source.getOrderedTime());
            map().setOrderStatus(source.getOrderStatus().getOrderStatusName());
            map().setTotalSum(source.getTotalAmount());
//            map().setDishList(source.getOrderedDishes());
        }
    };

    PropertyMap<OrderDTO, Order> DTOToOrderMapping = new PropertyMap<OrderDTO, Order>() {
        protected void configure() {
            map().getAddress().setAddressLine1(source.getAddressLine());
            map().getOrderStatus().setOrderStatusName((source.getOrderStatus()));
            map().setTotalAmount(source.getTotalSum());
            map().setOrderedTime(source.getOrderedDateTime());
//            map().setOrderedDishes(source.getDishList());
        }
    };

    public static OrderDTO convertOrderToDTO(Order order) {
        return modelMapper.map(order, OrderDTO.class);
    }

    public static Order convertDTOToOrder(OrderDTO orderDTO) {
        return modelMapper.map(orderDTO, Order.class);
    }

    public static List<OrderDTO> convertOrderListToDTO(List<Order> orderList) {
        return orderList.stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    public static List<Order> convertDTOListToOrder(List<OrderDTO> orderDTOList) {
        return orderDTOList.stream()
                .map(orderDTO -> modelMapper.map(orderDTO, Order.class))
                .collect(Collectors.toList());

    }
}
