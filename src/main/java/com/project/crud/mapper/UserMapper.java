package com.project.crud.mapper;

import com.project.crud.dto.UserDTO;
import com.project.crud.entity.User;

public class UserMapper {
    public static UserDTO mapToUserDTO(User user) {
        return new UserDTO(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    }

    public static User mapToUser(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getFirstName(), userDTO.getLastName(), userDTO.getEmail());
    }
}


