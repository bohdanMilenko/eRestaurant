package com.application.controller;

import com.application.entity.Order;
import com.application.entity.User;
import com.application.entity.dto.OrderDTO;
import com.application.entity.dto.UserDTO;
import com.application.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IUserService userService;

    public UserController() {
    }

    public UserController(ModelMapper modelMapper, IUserService userService) {
        this.modelMapper = modelMapper;
        this.userService = userService;
    }

    UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }


}
