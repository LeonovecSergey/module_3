package com.epam.jmp.service.controllers;

import com.epam.jmp.service.UserService;
import com.epam.jmp.service.dto.UserRequestDto;
import com.epam.jmp.service.dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/l2/api/v1.0/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User service L2", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of User.")
public class UserControllerL2 {

    private final UserService service;

    public UserControllerL2(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create new User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public UserResponseDto createUser(@RequestBody UserRequestDto request) {
        return service.createUser(request);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public UserResponseDto updateUser(@RequestBody UserRequestDto request) {
        return service.updateUser(request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public void deleteUser(@Parameter(description = "Id of the User to be deleted. Cannot be empty.")
                           @PathVariable Long id) {
        service.deleteUser(id);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public UserResponseDto getUser(@Parameter(description = "Id of the User to be obtained. Cannot be empty.")
                                   @PathVariable Long id) {
        return service.getUser(id);
    }

    @GetMapping
    @Operation(summary = "Returns list of all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users details retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public List<UserResponseDto> getAllUsers() {
        return service.getAllUsers();
    }
}
