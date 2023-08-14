package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.client.ProposalRestClient;
import br.com.henrique.mineradora.dto.ProposalClientDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDetailsDTO;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.UUID;

@ApplicationScoped
@Traced
public class ProposalServiceImpl implements ProposalService {

    @Inject
    @RestClient
    ProposalRestClient proposalRestClient;

    @Override
    public ProposalClientDetailsDto getProposalDetailsById(UUID proposalId) {
        return proposalRestClient.getProposalDetailsById(proposalId);
    }

    @Override
    public Response createProposal(ProposalDetailsDTO proposalDetails) {
        return proposalRestClient.createProposal(proposalDetails);
    }

    @Override
    public Response removeProposal(UUID id) {
        return proposalRestClient.removeProposal(id);

    }

}
