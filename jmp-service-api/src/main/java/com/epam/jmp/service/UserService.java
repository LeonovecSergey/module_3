package com.epam.jmp.service;

import com.epam.jmp.service.dto.UserRequestDto;
import com.epam.jmp.service.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto createUser(UserRequestDto request);

    UserResponseDto updateUser(UserRequestDto request);

    void deleteUser(Long id);

    UserResponseDto getUser(Long id);

    List<UserResponseDto> getAllUsers();
}
