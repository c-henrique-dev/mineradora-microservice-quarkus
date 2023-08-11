package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.ClienteDto;

import java.util.UUID;

public interface ClientService {

    ClienteDto createClient(ClienteDto clienteDto);
    ClienteDto findByUuid(UUID id);
    void deleteClientByUuid(UUID uuid);

}
