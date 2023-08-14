package br.com.henrique.mineradora.controller;

import br.com.henrique.mineradora.dto.ClienteDto;
import br.com.henrique.mineradora.service.ClientService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;
import java.util.UUID;

@Path("api/client")
@Authenticated
public class ClientController {

    @Inject
    ClientService clienteService;

    @POST
    @RolesAllowed({"user"})
    public Response createClient(ClienteDto clienteDto) {

        ClienteDto clienteDetails = clienteService.createClient(clienteDto);

        UriBuilder uriBuilder = UriBuilder.fromPath("api/client/{id}");
        URI uri = uriBuilder.build(clienteDetails.getId());

        return Response.created(uri).build();

    }

    @GET
    @Path("{id}")
    @RolesAllowed({"user"})
    public Response findByUuid(UUID id) {
        return Response.ok(this.clienteService.findByUuid(id)).build();
    }
}
