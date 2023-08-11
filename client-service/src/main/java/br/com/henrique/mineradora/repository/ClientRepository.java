package br.com.henrique.mineradora.repository;

import br.com.henrique.mineradora.entity.ClientEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<ClientEntity> {

    public ClientEntity findByUuid(UUID id) {

        return find("id", id).firstResult();
    }
    public void deleteByUuid(UUID uuid) {

        delete("id", uuid);
    }

}
