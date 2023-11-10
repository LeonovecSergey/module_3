package com.epam.jmp.service.impl.config;

import com.epam.jmp.service.impl.converter.SubscriptionRequestDtoToSubscriptionConverter;
import com.epam.jmp.service.impl.converter.SubscriptionToSubscriptionResponseDtoConverter;
import com.epam.jmp.service.impl.converter.UserRequestDtoToUserConverter;
import com.epam.jmp.service.impl.converter.UserToUserResponseDtoConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

@Configuration
public class WebConfig {

    @Bean
    public ConversionService conversionService() {
        var service = new DefaultConversionService();
        service.addConverter(new UserRequestDtoToUserConverter());
        service.addConverter(new UserToUserResponseDtoConverter());
        service.addConverter(new SubscriptionRequestDtoToSubscriptionConverter());
        service.addConverter(new SubscriptionToSubscriptionResponseDtoConverter());
        return service;
    }
}
