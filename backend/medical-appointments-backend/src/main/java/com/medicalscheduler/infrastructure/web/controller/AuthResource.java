package com.medicalscheduler.infrastructure.web.controller;

import com.medicalscheduler.infrastructure.web.dto.LoginRequest;
import com.medicalscheduler.infrastructure.web.dto.LoginResponse;
import com.medicalscheduler.infrastructure.web.dto.UserRequest;
import com.medicalscheduler.infrastructure.web.dto.UserResponse;
import com.medicalscheduler.service.AuthService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Authentication", description = "User registration and login operations")
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/register")
    @Operation(summary = "Register a new patient", description = "Creates a new user account with the provided personal information")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "User registered successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = UserResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response register(@Valid UserRequest request) {
        UserResponse response = authService.register(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @POST
    @Path("/login")
    @Operation(summary = "Authenticate a user", description = "Validates email and password credentials. Returns the user ID and name on success")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Login successful",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = LoginResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data"),
            @APIResponse(responseCode = "401", description = "Invalid email or password")
    })
    public LoginResponse login(@Valid LoginRequest request) {
        return authService.login(request);
    }
}
