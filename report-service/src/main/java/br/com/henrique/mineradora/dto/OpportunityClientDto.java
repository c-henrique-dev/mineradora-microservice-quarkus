package br.com.henrique.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Builder
@Data
public class OpportunityClientDto {

    private UUID proposalId;

    private ClientDto client;

    private BigDecimal priceTonne;

    private BigDecimal lastDollarQuotation;
}
