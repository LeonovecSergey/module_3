package com.epam.jmp.service.impl.converter;

import com.epam.jmp.service.dto.UserRequestDto;
import com.epam.jmp.service.entity.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UserRequestDtoToUserConverter implements Converter<UserRequestDto, User> {
    @Override
    public User convert(UserRequestDto source) {
        var userEntity = new User();
        userEntity.setBirthday(LocalDate.parse(source.getBirthday()));
        userEntity.setName(source.getName());
        userEntity.setSurname(source.getSurname());
        return userEntity;
    }
}
