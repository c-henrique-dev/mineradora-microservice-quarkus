package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.ClientDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public interface ClientService {
    ClientDto findByUuid(UUID id);
}
