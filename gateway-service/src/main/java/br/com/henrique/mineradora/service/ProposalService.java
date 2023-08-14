package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.ProposalClientDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDetailsDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.UUID;


@ApplicationScoped
public interface ProposalService {

    ProposalClientDetailsDto getProposalDetailsById(@PathParam("id") UUID proposalId);

    Response createProposal(ProposalDetailsDTO proposalDetails);

    Response removeProposal(UUID id);

}
