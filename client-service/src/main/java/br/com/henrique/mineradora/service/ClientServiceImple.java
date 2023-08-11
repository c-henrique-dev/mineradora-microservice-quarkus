package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.ClienteDto;
import br.com.henrique.mineradora.entity.ClientEntity;
import br.com.henrique.mineradora.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.apache.commons.beanutils.BeanUtils;

import java.util.UUID;

@ApplicationScoped
public class ClientServiceImple implements ClientService {

    @Inject
    ClientRepository clientRepository;

    @Override
    @Transactional
    public ClienteDto createClient(ClienteDto clienteDto) {
       try {
           ClientEntity client = new ClientEntity();
           BeanUtils.copyProperties(client, clienteDto);
           this.clientRepository.persist(client);
           clienteDto.setId(client.getId());
           return clienteDto;

       } catch (Exception e) {
           e.printStackTrace();
       }

       return null;

    }

    @Override
    public ClienteDto findByUuid(UUID id) {
        ClientEntity client = this.clientRepository.findByUuid(id);

        if(client != null) {
            return ClienteDto.builder().name(client.getName()).id(client.getId()).uf(client.getUf()).build();
        } else {
            throw new NotFoundException("Client not found");
        }

    }

    @Override
    public void deleteClientByUuid(UUID id) {

        ClientEntity client = this.clientRepository.findByUuid(id);

        if(client != null) {
            this.clientRepository.deleteByUuid(id);
        } else {
            throw new NotFoundException("Client not found");
        }

    }
}
