package com.epam.jmp.service.impl.service;

import com.epam.jmp.service.UserService;
import com.epam.jmp.service.dto.UserRequestDto;
import com.epam.jmp.service.dto.UserResponseDto;
import com.epam.jmp.service.entity.User;
import com.epam.jmp.service.exceptions.NotFoundException;
import com.epam.jmp.service.impl.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public UserServiceImpl(UserRepository userRepository, ConversionService conversionService) {
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public UserResponseDto createUser(UserRequestDto request) {
        var savedUser = userRepository.save(Objects.requireNonNull(conversionService.convert(request, User.class)));
        return conversionService.convert(savedUser, UserResponseDto.class);
    }

    @Override
    @Transactional
    public UserResponseDto updateUser(UserRequestDto request) {
        var user = userRepository.findById(request.getId())
                .orElseThrow(() -> NotFoundException.userNotFound(request.getId()));
        user.setName(request.getName());
        user.setSurname(request.getName());
        user.setBirthday(LocalDate.parse(request.getBirthday()));
        return conversionService.convert(userRepository.save(user), UserResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> NotFoundException.userNotFound(id));
        userRepository.delete(user);
    }

    @Override
    public UserResponseDto getUser(Long id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> NotFoundException.userNotFound(id));
        return conversionService.convert(user, UserResponseDto.class);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> conversionService.convert(user, UserResponseDto.class))
                .toList();
    }
}
