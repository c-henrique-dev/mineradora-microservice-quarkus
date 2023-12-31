package br.com.henrique.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@Jacksonized
public class ProposalDto {

    private UUID proposalId;

    private UUID client_id;

    private BigDecimal priceTonne;

}
