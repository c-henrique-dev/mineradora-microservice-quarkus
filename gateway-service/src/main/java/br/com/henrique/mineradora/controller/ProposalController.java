package br.com.henrique.mineradora.controller;

import br.com.henrique.mineradora.dto.ProposalDetailsDTO;
import br.com.henrique.mineradora.service.ProposalService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/api/trade")
public class ProposalController {

    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user"})
    public Response getProposalDetailsById(@PathParam("id") long id){
        proposalService.getProposalDetailsById(id);

            return Response.ok(proposalService.getProposalDetailsById(id),
                    MediaType.APPLICATION_JSON).build();

    }

    @POST
    @RolesAllowed("admin")
    public Response createNewProposal(ProposalDetailsDTO proposalDetails){
        return proposalService.createProposal(proposalDetails);
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("admin")
    public Response removeProposal(@PathParam("id") long id){
        return proposalService.removeProposal(id);


    }



}
