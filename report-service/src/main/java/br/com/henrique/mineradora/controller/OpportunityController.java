package br.com.henrique.mineradora.controller;

import br.com.henrique.mineradora.dto.OpportunityDto;
import br.com.henrique.mineradora.service.OpportunityService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
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
    @RolesAllowed({"user"})
    public List<OpportunityDto> generateReport(){
        return opportunityService.generateOpportunityData();

    }

}
