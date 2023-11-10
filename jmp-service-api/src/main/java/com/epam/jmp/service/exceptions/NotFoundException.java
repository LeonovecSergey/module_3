package com.epam.jmp.service.exceptions;

public final class NotFoundException extends RuntimeException {

    private static final String USER = "User with Id='%s' was not found";
    private static final String SUBSCRIPTION = "Subscription with Id='%s' was not found";

    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException userNotFound(Long userId) {
        return new NotFoundException(USER.formatted(userId));
    }

    public static NotFoundException subscriptionNotFound(Long subscriptionId) {
        return new NotFoundException(SUBSCRIPTION.formatted(subscriptionId));
    }

}
