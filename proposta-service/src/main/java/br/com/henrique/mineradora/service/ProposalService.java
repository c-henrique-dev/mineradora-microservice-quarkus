package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.ProposalDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public interface ProposalService {

    ProposalDetailsDto findFullProposal(UUID id);

    ProposalDto createNewProposal(ProposalDetailsDto proposalDetailsDTO);

    void removeProposal(UUID id);

}
