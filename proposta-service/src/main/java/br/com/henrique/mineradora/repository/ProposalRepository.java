package br.com.henrique.mineradora.repository;

import br.com.henrique.mineradora.entity.ProposalEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ProposalRepository implements PanacheRepository<ProposalEntity> {

    public Optional<ProposalEntity> findByCustomer(String customer){
        return Optional.of(find("customer", customer).firstResult());
    }
    public ProposalEntity findByUuid(UUID uuid) {
        return find("id", uuid).firstResult();
    }
    public void deleteByUuid(UUID uuid) {
        delete("id", uuid);
    }
}
