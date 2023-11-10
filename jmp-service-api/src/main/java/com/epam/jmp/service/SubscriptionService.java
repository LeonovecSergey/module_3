package com.epam.jmp.service;

import com.epam.jmp.service.dto.SubscriptionRequestDto;
import com.epam.jmp.service.dto.SubscriptionResponseDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResponseDto createSubscription(SubscriptionRequestDto request);

    SubscriptionResponseDto updateSubscription(SubscriptionRequestDto request);

    void deleteSubscription(Long id);

    SubscriptionResponseDto getSubscription(Long id);

    List<SubscriptionResponseDto> getAllUsers();
}
