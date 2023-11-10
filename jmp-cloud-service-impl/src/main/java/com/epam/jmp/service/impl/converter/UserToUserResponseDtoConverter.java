package com.epam.jmp.service.impl.converter;

import com.epam.jmp.service.dto.UserResponseDto;
import com.epam.jmp.service.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToUserResponseDtoConverter implements Converter<User, UserResponseDto> {
    @Override
    public UserResponseDto convert(User source) {
        var dto = new UserResponseDto();
        dto.setId(source.getId());
        dto.setBirthday(source.getBirthday().toString());
        dto.setName(source.getName());
        dto.setSurname(source.getSurname());
        return dto;
    }
}
