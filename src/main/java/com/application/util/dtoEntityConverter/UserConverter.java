package com.application.util.dtoEntityConverter;

import com.application.entity.User;
import com.application.entity.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {


    private static ModelMapper modelMapper;

    public UserConverter() {
    }

    @Autowired
    public UserConverter(ModelMapper modelMapper) {
        UserConverter.modelMapper = modelMapper;
    }


    public static UserDTO convertToDto(User user) {
        return modelMapper.map(user, UserDTO.class);
    }

    public static User convertToEntity(UserDTO userDTO) {
        return modelMapper.map(userDTO, User.class);
    }

}
