package br.com.henrique.controller;

import br.com.henrique.dto.ProposalDetailsDto;
import br.com.henrique.service.ProposalService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/api/proposal")
@Authenticated
public class ProposalController {

    private final Logger LOG = LoggerFactory.getLogger(ProposalController.class);

    @Inject
    JsonWebToken jsonWebToken;
    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user","manager"})

    public ProposalDetailsDto findDetailsProposal(@PathParam("id") long id){
        return proposalService.findFullProposal(id);
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDto proposalDetails){

        LOG.info("--- Recebendo Proposta de Compra ---");

        try {

            proposalService.createNewProposal(proposalDetails);
            return Response.ok().build();

        } catch (Exception e) {

            return Response.serverError().build();

        }

    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id){

        try {
            proposalService.removeProposal(id);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

}
