package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.*;
import br.com.henrique.mineradora.entity.OpportunityEntity;
import br.com.henrique.mineradora.entity.QuotationEntity;
import br.com.henrique.mineradora.repository.OpportunityRepository;
import br.com.henrique.mineradora.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class OpportunityServiceImple implements OpportunityService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    @Inject
    ClientService clientService;

    @Override
    public void buildOpportunity(ProposalDto proposal) {
        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        Collections.reverse(quotationEntities);

        OpportunityEntity opportunity = new OpportunityEntity();

        opportunity.setDate(new Date());
        opportunity.setProposalId(proposal.getProposalId());
        opportunity.setClientId(proposal.getClient_id());
        opportunity.setPriceTonne(proposal.getPriceTonne());
        opportunity.setLastDollarQuotation(quotationEntities.get(0).getCurrencyPrice());

        opportunityRepository.persist(opportunity);

    }

    @Override
    @Transactional
    public void saveQuotation(QuotationDto quotation) {

        QuotationEntity createQuotation = new QuotationEntity();
        createQuotation.setDate(new Date());
        createQuotation.setCurrencyPrice(quotation.getCurrencyPrice());

        quotationRepository.persist(createQuotation);

    }

    @Override
    public List<OpportunityClientDto> generateOpportunityData() {

        List<OpportunityClientDto> opportunities = new ArrayList<>();


        opportunityRepository
                .findAll()
                .stream()
                .forEach(item -> {
                    ClientDto clientDto = this.clientService.findByUuid(item.getClientId());

                    opportunities.add(OpportunityClientDto.builder()
                            .proposalId(item.getProposalId())
                            .client(clientDto)
                            .priceTonne(item.getPriceTonne())
                            .lastDollarQuotation(item.getLastDollarQuotation())
                            .build());
                });

        return opportunities;

    }

}
