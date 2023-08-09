package br.com.henrique.mineradora.service;

import br.com.henrique.mineradora.client.ReportRestClient;
import br.com.henrique.mineradora.dto.OpportunityDTO;
import br.com.henrique.mineradora.utils.CSVHelper;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.ByteArrayInputStream;
import java.util.List;

@ApplicationScoped
@Traced
public class ReportServiceImpl implements ReportService {

    @Inject
    @RestClient
    ReportRestClient reportRestClient;

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {

        List<OpportunityDTO> opportunityData = reportRestClient.requestOpportunitiesData();
        return CSVHelper.OpportunitiesToCSV(opportunityData);

    }

    @Override
    public List<OpportunityDTO> getOpportunitiesData() {

        return reportRestClient.requestOpportunitiesData();

    }

}
