package br.com.henrique.mineradora.service;

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

import java.util.Date;
import java.util.UUID;

@ApplicationScoped
public class ProposalServiceImple implements ProposalService {

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaMessages;

    @Override
    public ProposalDetailsDto findFullProposal(UUID id) {
        ProposalEntity proposal = proposalRepository.findByUuid(id);

        if(proposal != null) {
            return ProposalDetailsDto.builder()
                    .proposalId(proposal.getId())
                    .proposalValidityDays(proposal.getProposalValidityDays())
                    .country(proposal.getCountry())
                    .priceTonne(proposal.getPriceTonne())
                    .customer(proposal.getCustomer())
                    .tonnes(proposal.getTonnes())
                    .build();
        } else {
            throw new NotFoundException("Proposal not found");
        }
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
        ProposalEntity proposal = proposalRepository.findByUuid(id);
        if(proposal != null) {

            proposalRepository.deleteByUuid(proposal.getId());

        } else {
            throw new NotFoundException("Proposal not found");
        }
    }

    @Transactional
    protected ProposalDto buildAndSaveNewProposal(ProposalDetailsDto proposalDetailsDto){

        try {
            ProposalEntity proposal = new ProposalEntity();
            proposal.setCreated(new Date());
            BeanUtils.copyProperties(proposal, proposalDetailsDto);
            System.out.println(proposal.getTonnes());
            proposalRepository.persist(proposal);

            return ProposalDto.builder()
                    .proposalId(proposalRepository.findByCustomer(proposal.getCustomer()).get().getId())
                    .priceTonne(proposal.getPriceTonne())
                    .customer(proposal.getCustomer())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

    }

}
