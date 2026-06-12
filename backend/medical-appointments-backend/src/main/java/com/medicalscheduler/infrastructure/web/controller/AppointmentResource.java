package com.medicalscheduler.infrastructure.web.controller;

import com.medicalscheduler.infrastructure.web.dto.AppointmentRequest;
import com.medicalscheduler.infrastructure.web.dto.AppointmentResponse;
import com.medicalscheduler.service.AppointmentService;
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

import java.util.List;

@Path("/api/appointments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Appointment", description = "Operations related to appointment booking and management")
public class AppointmentResource {

    @Inject
    AppointmentService service;

    @POST
    @Operation(summary = "Book a new appointment", description = "Creates a new appointment after validating business rules: no duplicate specialty per day for the same patient, and no double-booking the same time slot")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Appointment created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = AppointmentResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data"),
            @APIResponse(responseCode = "404", description = "User or doctor not found"),
            @APIResponse(responseCode = "409", description = "Conflict: duplicate specialty or time slot already booked")
    })
    public Response create(@Valid AppointmentRequest request) {
        AppointmentResponse response = service.create(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @GET
    @Path("/user/{userId}")
    @Operation(summary = "List appointments by user", description = "Returns the dashboard list of scheduled appointments for a specific patient")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Appointments retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = AppointmentResponse.class))),
            @APIResponse(responseCode = "404", description = "User not found")
    })
    public List<AppointmentResponse> findByUserId(@PathParam("userId") Integer userId) {
        return service.findByUserId(userId);
    }
}
