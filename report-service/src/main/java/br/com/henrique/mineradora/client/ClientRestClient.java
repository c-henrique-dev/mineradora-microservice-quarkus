package br.com.henrique.mineradora.client;

import br.com.henrique.mineradora.dto.ClientDto;
import io.quarkus.oidc.token.propagation.reactive.AccessTokenRequestReactiveFilter;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.UUID;

@Path("/api/client")
@RegisterRestClient
@RegisterProvider(AccessTokenRequestReactiveFilter.class)
@ApplicationScoped
public interface ClientRestClient {

    @GET
    @Path("/{id}")
    ClientDto findByUuid(@PathParam("id") UUID id);
}
