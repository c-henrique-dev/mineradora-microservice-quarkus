package br.com.henrique.mineradora.client;

import br.com.henrique.mineradora.dto.ProposalClientDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDetailsDTO;
import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/api/proposal")
@RegisterRestClient
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
@ApplicationScoped
public interface ProposalRestClient {
    @GET
    @Path("/{id}")
    ProposalClientDetailsDto getProposalDetailsById(@PathParam("id") UUID proposalId);

    @POST
    Response createProposal(ProposalDetailsDTO proposalDetails);

    @DELETE
    @Path("/{id}")
    Response removeProposal(@PathParam("id") UUID id);

}
