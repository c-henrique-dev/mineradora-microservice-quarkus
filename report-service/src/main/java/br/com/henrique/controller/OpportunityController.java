package br.com.henrique.controller;

import br.com.henrique.dto.OpportunityDto;
import br.com.henrique.service.OpportunityService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.List;

@Path("/api/opportunity")
@Authenticated
public class OpportunityController {
    @Inject
    OpportunityService opportunityService;
    @Inject
    JsonWebToken jsonWebToken;
    @GET
    @Path("/data")
    @RolesAllowed({"user","manager"})
    public List<OpportunityDto> generateReport(){
        return opportunityService.generateOpportunityData();

    }

}
