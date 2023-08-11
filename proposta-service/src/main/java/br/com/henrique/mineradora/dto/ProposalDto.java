package br.com.henrique.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Data
@Builder
public class ProposalDto {

    private UUID proposalId;

    private UUID client_id;

    private BigDecimal priceTonne;

}