package br.com.henrique.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Jacksonized
@AllArgsConstructor
@Data
@Builder
public class ProposalDto {

    private Long proposalId;

    private String customer;

    private BigDecimal priceTonne;

}