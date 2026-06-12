package com.medicalscheduler.infrastructure.web.controller;

import com.medicalscheduler.infrastructure.web.dto.AvailabilityResponse;
import com.medicalscheduler.infrastructure.web.dto.DoctorRequest;
import com.medicalscheduler.infrastructure.web.dto.DoctorResponse;
import com.medicalscheduler.service.DoctorService;
import com.medicalscheduler.service.ScheduleService;
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

@Path("/api/doctors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Doctor", description = "Operations related to doctors")
public class DoctorResource {

    @Inject
    DoctorService service;

    @Inject
    ScheduleService scheduleService;

    @GET
    @Operation(summary = "List all doctors", description = "Returns a list of all registered doctors")
    @APIResponse(responseCode = "200", description = "List of doctors retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = DoctorResponse.class)))
    public List<DoctorResponse> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get doctor by ID", description = "Returns a single doctor by their unique identifier")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Doctor found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DoctorResponse.class))),
            @APIResponse(responseCode = "404", description = "Doctor not found")
    })
    public DoctorResponse findById(@PathParam("id") Integer id) {
        return service.findById(id);
    }

    @POST
    @Operation(summary = "Create a new doctor", description = "Creates a new doctor and returns the created resource")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Doctor created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DoctorResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response create(@Valid DoctorRequest request) {
        DoctorResponse response = service.create(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update an existing doctor", description = "Updates the details of an existing doctor by their ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Doctor updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DoctorResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data"),
            @APIResponse(responseCode = "404", description = "Doctor not found")
    })
    public DoctorResponse update(@PathParam("id") Integer id, @Valid DoctorRequest request) {
        return service.update(id, request);
    }

    @GET
    @Path("/{id}/availability")
    @Operation(summary = "Get doctor availability", description = "Returns available 15-minute time slots for the next 6 business days based on the doctor's recurring schedule")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Availability retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = AvailabilityResponse.class))),
            @APIResponse(responseCode = "404", description = "Doctor not found")
    })
    public AvailabilityResponse getAvailability(@PathParam("id") Integer id) {
        return scheduleService.getAvailability(id);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a doctor", description = "Deletes a doctor by their unique identifier")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Doctor deleted successfully"),
            @APIResponse(responseCode = "404", description = "Doctor not found")
    })
    public Response delete(@PathParam("id") Integer id) {
        service.delete(id);
        return Response.noContent().build();
    }
}
