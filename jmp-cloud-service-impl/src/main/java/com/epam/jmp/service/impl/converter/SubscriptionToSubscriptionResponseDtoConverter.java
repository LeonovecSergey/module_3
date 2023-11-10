package com.epam.jmp.service.impl.converter;

import com.epam.jmp.service.dto.SubscriptionResponseDto;
import com.epam.jmp.service.entity.Subscription;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionToSubscriptionResponseDtoConverter implements Converter<Subscription, SubscriptionResponseDto> {

    @Override
    public SubscriptionResponseDto convert(Subscription source) {
        var dto = new SubscriptionResponseDto();
        dto.setId(source.getId());
        dto.setUserId(source.getUser().getId());
        dto.setStartDate(source.getStartDate().toString());
        return dto;
    }
}
