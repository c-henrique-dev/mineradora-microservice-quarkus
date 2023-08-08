package br.com.henrique.service;

import br.com.henrique.dto.ProposalDetailsDto;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface ProposalService {

    ProposalDetailsDto findFullProposal(long id);

    void createNewProposal(ProposalDetailsDto proposalDetailsDTO);

    void removeProposal(long id);

}
