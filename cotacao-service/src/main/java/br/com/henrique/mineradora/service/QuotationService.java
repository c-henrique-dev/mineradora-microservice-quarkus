package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.client.CurrencyPriceClient;
import br.com.henrique.mineradora.entity.QuotationEntity;
import br.com.henrique.mineradora.message.KafkaEvents;
import br.com.henrique.mineradora.dto.CurrencyPriceDto;
import br.com.henrique.mineradora.dto.QuotationDto;
import br.com.henrique.mineradora.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@ApplicationScoped
public class QuotationService {
    @Inject
    @RestClient
    CurrencyPriceClient currencyPriceClient;
    @Inject
     QuotationRepository quotationRepository;
    @Inject
    KafkaEvents kafkaEvents;

    public void getCurrencyPrice() {
        CurrencyPriceDto currencyPriceInfo = currencyPriceClient.getPriceByPair("USD-BRL");
        if (updateCurrentInfoPrice(currencyPriceInfo)) {
            kafkaEvents.sendNewKafkaEvent(QuotationDto
                    .builder()
                    .currencyPrice(new BigDecimal((currencyPriceInfo.getUSDBRL().getBid())))
                    .date(new Date())
                    .build());

        }
    }

    private boolean updateCurrentInfoPrice(CurrencyPriceDto currencyPriceDto) {
        BigDecimal currentPrice = new BigDecimal(currencyPriceDto.getUSDBRL().getBid());
        boolean updatePrice = false;

        List<QuotationEntity> quotationList = quotationRepository.findAll().list();

        if (quotationList.isEmpty()) {
            saveQuotation(currencyPriceDto);
            updatePrice = true;

        } else {
            QuotationEntity lastDollarPrice = quotationList
                    .get(quotationList.size() - 1);

            if (currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()) {
                updatePrice = true;
                saveQuotation(currencyPriceDto);

            }

        }

        return updatePrice;

    }

    private void saveQuotation(CurrencyPriceDto currencyPriceDto) {
        QuotationEntity quotationEntity = new QuotationEntity();

        quotationEntity.setDate(new Date());
        quotationEntity.setCurrencyPrice(new BigDecimal(currencyPriceDto.getUSDBRL().getBid()));
        quotationEntity.setPctChange(currencyPriceDto.getUSDBRL().getPctChange());
        quotationEntity.setPair("USD-BRL");
        quotationRepository.persist(quotationEntity);
    }
}
