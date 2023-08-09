package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.OpportunityDto;
import br.com.henrique.mineradora.dto.ProposalDto;
import br.com.henrique.mineradora.dto.QuotationDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public interface OpportunityService {

    void buildOpportunity(ProposalDto proposal);

    void saveQuotation(QuotationDto quotation);

    List<OpportunityDto> generateOpportunityData();

}
