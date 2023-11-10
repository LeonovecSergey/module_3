package com.epam.jmp.service.controllers;

import com.epam.jmp.service.UserService;
import com.epam.jmp.service.controllers.advice.ErrorResponse;
import com.epam.jmp.service.dto.RequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/l0/api/v1.0/user", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "User service L0", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of User.")
public class UserControllerL0 {

    private final UserService service;

    public UserControllerL0(UserService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Creating, Retrieving, Updating and Deleting of User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Created, Retrieved, Updated and Deleted", content = @Content),
            @ApiResponse(responseCode = "200", description = "Internal Server Error", content = @Content)
    })
    public ResponseEntity<?> user(@RequestBody RequestDto request) {

        var response = switch (request.getAction()) {
            case CREATE -> service.createUser(request.getUser());
            case UPDATE -> service.updateUser(request.getUser());
            case GET -> service.getUser(request.getUser().getId());
            case GET_ALL -> service.getAllUsers();
        };

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> exceptionResolver(Exception e) {
        return ResponseEntity.ok(new ErrorResponse(e));
    }


}
