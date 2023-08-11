package br.com.henrique.mineradora.controller;

import br.com.henrique.mineradora.dto.ClientDto;
import br.com.henrique.mineradora.dto.ProposalClientDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDto;
import br.com.henrique.mineradora.service.ClientService;
import br.com.henrique.mineradora.service.ProposalService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.UUID;

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
    @RolesAllowed({"user"})
    public ProposalClientDetailsDto findDetailsProposal(@PathParam("id") UUID id){
        return proposalService.findFullProposal(id);
    }

    @POST
    @RolesAllowed("admin")
    public Response createProposal(ProposalDetailsDto proposalDetails){

        LOG.info("--- Recebendo Proposta de Compra ---");

        ProposalDto proposalDetailsDto = proposalService.createNewProposal(proposalDetails);

        UriBuilder uriBuilder = UriBuilder.fromPath("/api/proposal/{id}");
        URI uri = uriBuilder.build(proposalDetailsDto.getProposalId());

        return Response.created(uri).build();

    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    public Response removeProposal(@PathParam("id") UUID id){
        proposalService.removeProposal(id);
        return Response.ok().build();
    }

}
