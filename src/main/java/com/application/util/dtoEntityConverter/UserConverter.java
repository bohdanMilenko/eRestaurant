package com.application.util.dtoEntityConverter;

import com.application.entity.User;
import com.application.entity.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserConverter() {
    }

    public UserConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
