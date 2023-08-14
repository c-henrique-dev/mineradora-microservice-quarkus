package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.dto.ClientDto;
import br.com.henrique.mineradora.dto.ProposalClientDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDetailsDto;
import br.com.henrique.mineradora.dto.ProposalDto;
import br.com.henrique.mineradora.entity.ProposalEntity;
import br.com.henrique.mineradora.message.KafkaEvent;
import br.com.henrique.mineradora.repository.ProposalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.apache.commons.beanutils.BeanUtils;
import org.eclipse.microprofile.opentracing.Traced;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@Traced
public class ProposalServiceImple implements ProposalService {

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    ClientService clientService;

    @Inject
    KafkaEvent kafkaMessages;

    @Override
    public ProposalClientDetailsDto findFullProposal(UUID id) {
        ProposalEntity proposal = proposalRepository.findByUuid(id)
                .orElseThrow(() -> new NotFoundException("Proposal not found"));

        ClientDto clientDto = this.clientService.findByUuid(proposal.getClient_id());

                return ProposalClientDetailsDto.builder()
                        .proposalId(proposal.getId())
                        .proposalValidityDays(proposal.getProposalValidityDays())
                        .country(proposal.getCountry())
                        .client(clientDto)
                        .priceTonne(proposal.getPriceTonne())
                        .tonnes(proposal.getTonnes())
                        .build();

    }

    @Override
    @Transactional
    public ProposalDto createNewProposal(ProposalDetailsDto proposalDetailsDTO) {
        ProposalDto proposal = buildAndSaveNewProposal(proposalDetailsDTO);
        kafkaMessages.sendNewKafkaEvent(proposal);
        return proposal;

    }

    @Override
    @Transactional
    public void removeProposal(UUID id) {
        Optional<ProposalEntity> proposal = proposalRepository.findByUuid(id);
        if(proposal != null) {

            proposalRepository.deleteByUuid(proposal.get().getId());

        } else {
            throw new RuntimeException("Proposal not found");
        }
    }

    @Transactional
    protected ProposalDto buildAndSaveNewProposal(ProposalDetailsDto proposalDetailsDto){

        try {
            ProposalEntity proposal = new ProposalEntity();
            proposal.setCreated(new Date());
            BeanUtils.copyProperties(proposal, proposalDetailsDto);
            proposalRepository.persist(proposal);

            return ProposalDto.builder()
                    .proposalId(clientService.findByUuid(proposal.getClient_id()).getId())
                    .priceTonne(proposal.getPriceTonne())
                    .client_id(proposal.getClient_id())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}
