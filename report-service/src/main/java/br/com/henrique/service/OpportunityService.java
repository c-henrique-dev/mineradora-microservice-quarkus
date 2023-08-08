package br.com.henrique.service;

import br.com.henrique.dto.OpportunityDto;
import br.com.henrique.dto.ProposalDto;
import br.com.henrique.dto.QuotationDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
public interface OpportunityService {

    void buildOpportunity(ProposalDto proposal);

    void saveQuotation(QuotationDto quotation);

    List<OpportunityDto> generateOpportunityData();

}
