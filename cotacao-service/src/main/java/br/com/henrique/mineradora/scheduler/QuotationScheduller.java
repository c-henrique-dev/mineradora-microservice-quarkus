package br.com.henrique.mineradora.scheduler;

import br.com.henrique.mineradora.service.QuotationService;
import io.quarkus.scheduler.Scheduled;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class QuotationScheduller {

    @Inject
    private QuotationService quotationService;

    @Transactional
    @Scheduled(every = "35s", identity = "task-job")
    void scheduller() {
        quotationService.getCurrencyPrice();
    }

}
