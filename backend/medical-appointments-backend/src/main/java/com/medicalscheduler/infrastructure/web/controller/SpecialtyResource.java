package com.medicalscheduler.infrastructure.web.controller;

import com.medicalscheduler.infrastructure.web.dto.DoctorResponse;
import com.medicalscheduler.infrastructure.web.dto.SpecialtyRequest;
import com.medicalscheduler.infrastructure.web.dto.SpecialtyResponse;
import com.medicalscheduler.service.DoctorService;
import com.medicalscheduler.service.SpecialtyService;
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

@Path("/api/specialties")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Specialty", description = "Operations related to medical specialties")
public class SpecialtyResource {

    @Inject
    SpecialtyService specialtyService;

    @Inject
    DoctorService doctorService;

    @GET
    @Operation(summary = "List all specialties", description = "Returns all medical specialties. Each specialty includes a flag indicating whether it has available doctors with active schedules")
    @APIResponse(responseCode = "200", description = "List of specialties retrieved successfully",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = SpecialtyResponse.class)))
    public List<SpecialtyResponse> findAll() {
        return specialtyService.findAll();
    }

    @GET
    @Path("/{specialtyId}/doctors")
    @Operation(summary = "List doctors by specialty", description = "Returns all doctors that belong to a given medical specialty")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Doctors retrieved successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = DoctorResponse.class))),
            @APIResponse(responseCode = "404", description = "Specialty not found")
    })
    public List<DoctorResponse> findDoctorsBySpecialty(@PathParam("specialtyId") Integer specialtyId) {
        return doctorService.findBySpecialtyId(specialtyId);
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Get specialty by ID", description = "Returns a single medical specialty by its unique identifier")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Specialty found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SpecialtyResponse.class))),
            @APIResponse(responseCode = "404", description = "Specialty not found")
    })
    public SpecialtyResponse findById(@PathParam("id") Integer id) {
        return specialtyService.findById(id);
    }

    @POST
    @Operation(summary = "Create a new specialty", description = "Creates a new medical specialty and returns the created resource")
    @APIResponses({
            @APIResponse(responseCode = "201", description = "Specialty created successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SpecialtyResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data")
    })
    public Response create(@Valid SpecialtyRequest request) {
        SpecialtyResponse response = specialtyService.create(request);
        return Response.status(Response.Status.CREATED).entity(response).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update an existing specialty", description = "Updates the details of an existing medical specialty by its ID")
    @APIResponses({
            @APIResponse(responseCode = "200", description = "Specialty updated successfully",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = SpecialtyResponse.class))),
            @APIResponse(responseCode = "400", description = "Invalid input data"),
            @APIResponse(responseCode = "404", description = "Specialty not found")
    })
    public SpecialtyResponse update(@PathParam("id") Integer id, @Valid SpecialtyRequest request) {
        return specialtyService.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a specialty", description = "Deletes a medical specialty by its unique identifier")
    @APIResponses({
            @APIResponse(responseCode = "204", description = "Specialty deleted successfully"),
            @APIResponse(responseCode = "404", description = "Specialty not found")
    })
    public Response delete(@PathParam("id") Integer id) {
        specialtyService.delete(id);
        return Response.noContent().build();
    }
}
