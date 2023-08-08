package br.com.henrique.service;

import br.com.henrique.dto.ProposalDetailsDto;
import br.com.henrique.dto.ProposalDto;
import br.com.henrique.entity.ProposalEntity;
import br.com.henrique.message.KafkaEvent;
import br.com.henrique.repository.ProposalRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.Date;

@ApplicationScoped
public class ProposalServiceImple implements ProposalService {

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaMessages;

    @Override
    public ProposalDetailsDto findFullProposal(long id) {

        ProposalEntity proposal = proposalRepository.findById(id);

        return ProposalDetailsDto.builder()
                .proposalId(proposal.getId())
                .proposalValidityDays(proposal.getProposalValidityDays())
                .country(proposal.getCountry())
                .priceTonne(proposal.getPriceTonne())
                .customer(proposal.getCustomer())
                .tonnes(proposal.getTonnes())
                .build();

    }

    @Override
    @Transactional
    public void createNewProposal(ProposalDetailsDto proposalDetailsDTO) {

        ProposalDto proposal = buildAndSaveNewProposal(proposalDetailsDTO);
        kafkaMessages.sendNewKafkaEvent(proposal);

    }

    @Override
    @Transactional
    public void removeProposal(long id) {
        proposalRepository.deleteById(id);
    }

    @Transactional
    protected ProposalDto buildAndSaveNewProposal(ProposalDetailsDto proposalDetailsDto){

        try {

            ProposalEntity proposal = new ProposalEntity();

            proposal.setCreated(new Date());
            proposal.setProposalValidityDays(proposalDetailsDto.getProposalValidityDays());
            proposal.setCountry(proposalDetailsDto.getCountry());
            proposal.setCustomer(proposalDetailsDto.getCustomer());
            proposal.setPriceTonne(proposalDetailsDto.getPriceTonne());
            proposal.setTonnes(proposalDetailsDto.getTonnes());

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
