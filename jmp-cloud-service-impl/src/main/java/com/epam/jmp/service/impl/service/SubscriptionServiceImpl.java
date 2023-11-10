package com.epam.jmp.service.impl.service;

import com.epam.jmp.service.SubscriptionService;
import com.epam.jmp.service.dto.SubscriptionRequestDto;
import com.epam.jmp.service.dto.SubscriptionResponseDto;
import com.epam.jmp.service.entity.Subscription;
import com.epam.jmp.service.exceptions.NotFoundException;
import com.epam.jmp.service.impl.repository.SubscriptionRepository;
import com.epam.jmp.service.impl.repository.UserRepository;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final ConversionService conversionService;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, UserRepository userRepository,
                                   ConversionService conversionService) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
        this.conversionService = conversionService;
    }

    @Override
    @Transactional
    public SubscriptionResponseDto createSubscription(SubscriptionRequestDto request) {
        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> NotFoundException.userNotFound(request.getUserId()));
        var subscription = conversionService.convert(request, Subscription.class);
        subscription.setUser(user);
        return conversionService.convert(subscriptionRepository.save(subscription), SubscriptionResponseDto.class);
    }

    @Override
    @Transactional
    public SubscriptionResponseDto updateSubscription(SubscriptionRequestDto request) {
        var subscription = subscriptionRepository.findById(request.getId())
                .orElseThrow(() -> NotFoundException.subscriptionNotFound(request.getId()));
        var user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> NotFoundException.userNotFound(request.getUserId()));
        subscription.setUser(user);
        return conversionService.convert(subscriptionRepository.save(subscription), SubscriptionResponseDto.class);
    }

    @Override
    @Transactional
    public void deleteSubscription(Long id) {
        var subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> NotFoundException.subscriptionNotFound(id));
        subscriptionRepository.delete(subscription);
    }

    @Override
    public SubscriptionResponseDto getSubscription(Long id) {
        var subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> NotFoundException.subscriptionNotFound(id));
        return conversionService.convert(subscription, SubscriptionResponseDto.class);
    }

    @Override
    public List<SubscriptionResponseDto> getAllUsers() {
        return subscriptionRepository.findAll().stream()
                .map(subscription -> conversionService.convert(subscription, SubscriptionResponseDto.class))
                .toList();
    }
}
