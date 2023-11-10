package com.epam.jmp.service.dto;

public class RequestDto {

    private Action action;

    private UserRequestDto user;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public UserRequestDto getUser() {
        return user;
    }

    public void setUser(UserRequestDto user) {
        this.user = user;
    }

    public enum Action {
        CREATE,
        UPDATE,
        GET,
        GET_ALL
    }
}
