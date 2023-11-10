package com.epam.jmp.service.controllers;

import com.epam.jmp.service.SubscriptionService;
import com.epam.jmp.service.dto.SubscriptionRequestDto;
import com.epam.jmp.service.dto.SubscriptionResponseDto;
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
@RequestMapping(value = "/l2/api/v1.0/subscription", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Subscription service L2", description = "Set of endpoints for Creating, Retrieving, Updating and Deleting of Subscription.")
public class SubscriptionControllerL2 {

    private final SubscriptionService service;

    public SubscriptionControllerL2(SubscriptionService service) {
        this.service = service;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(summary = "Create new Subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public SubscriptionResponseDto createSubscription(@RequestBody SubscriptionRequestDto request) {
        return service.createSubscription(request);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = "Update Subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public SubscriptionResponseDto updateSubscription(@RequestBody SubscriptionRequestDto request) {
        return service.updateSubscription(request);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete Subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Subscription deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public void deleteSubscription(@Parameter(description = "Id of the Subscription to be deleted. Cannot be empty.")
                                   @PathVariable Long id) {
        service.deleteSubscription(id);
    }

    @GetMapping(path = "/{id}")
    @Operation(summary = "Get Subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription details retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = SubscriptionResponseDto.class))}),
            @ApiResponse(responseCode = "404", description = "Subscription not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public SubscriptionResponseDto getSubscription(@Parameter(description = "Id of the Subscription to be obtained. Cannot be empty.")
                                                   @PathVariable Long id) {
        return service.getSubscription(id);
    }

    @GetMapping
    @Operation(summary = "Returns list of all subscriptions")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscriptions details retrieved",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    public List<SubscriptionResponseDto> getAllUsers() {
        return service.getAllUsers();
    }
}
