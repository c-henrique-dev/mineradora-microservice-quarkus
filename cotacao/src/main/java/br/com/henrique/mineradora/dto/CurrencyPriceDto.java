package br.com.henrique.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@Builder
@AllArgsConstructor
public class CurrencyPriceDto {
    public USDBRL USDBRL;
}
