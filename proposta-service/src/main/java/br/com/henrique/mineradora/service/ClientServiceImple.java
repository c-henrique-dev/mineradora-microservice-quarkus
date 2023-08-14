package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.client.ClientRestClient;
import br.com.henrique.mineradora.dto.ClientDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.UUID;
@ApplicationScoped
@Traced
public class ClientServiceImple implements ClientService{
    @Inject
    @RestClient
    ClientRestClient clientRestClient;

    @Override
    public ClientDto findByUuid(UUID id) {
        return this.clientRestClient.findByUuid(id);
    }
}
