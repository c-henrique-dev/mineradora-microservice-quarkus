package br.com.henrique.repository;

import br.com.henrique.entity.QuotationEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class QuotationRepository implements PanacheRepository<QuotationEntity> {
}
