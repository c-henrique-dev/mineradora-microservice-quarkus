package br.com.henrique.mineradora.message;

import br.com.henrique.mineradora.dto.ProposalDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvent {

    private final Logger LOG = LoggerFactory.getLogger(KafkaEvent.class);

    @Channel("proposal")
    Emitter<ProposalDto> proposalRequestEmitter;

    public void sendNewKafkaEvent(ProposalDto proposalDTO){
        LOG.info("-- Enviando Nova Proposta para Tópico Kafka --");
        proposalRequestEmitter.send(proposalDTO).toCompletableFuture().join();;
    }

}
