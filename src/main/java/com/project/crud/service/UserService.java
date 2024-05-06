package com.project.crud.service;

import com.project.crud.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO getUserById(Long userId);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long userId, UserDTO updatedUserDTO);

    void deleteUser(Long userId);
}
