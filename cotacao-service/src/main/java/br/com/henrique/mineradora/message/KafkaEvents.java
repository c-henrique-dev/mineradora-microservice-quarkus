package br.com.henrique.mineradora.message;

import br.com.henrique.mineradora.dto.QuotationDto;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class KafkaEvents {
    private final Logger logger = LoggerFactory.getLogger(KafkaEvents.class);
    @Channel("quotation-channel")
    Emitter<QuotationDto> quationRequestEmitter;

    public void sendNewKafkaEvent(QuotationDto quotationDto) {

        logger.info("--- Enviando cotação para Tópico Kafka ---");
        quationRequestEmitter.send(quotationDto).toCompletableFuture().join();

    }

}
