package com.epam.jmp.service.impl.converter;

import com.epam.jmp.service.dto.SubscriptionRequestDto;
import com.epam.jmp.service.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class SubscriptionRequestDtoToSubscriptionConverter implements Converter<SubscriptionRequestDto, Subscription> {

    @Override
    public Subscription convert(SubscriptionRequestDto source) {
        var entity = new Subscription();
        entity.setStartDate(LocalDate.now());
        return entity;
    }
}
