package br.com.henrique.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Jacksonized
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyPriceDto {
    public USDBRL USDBRL;
}
