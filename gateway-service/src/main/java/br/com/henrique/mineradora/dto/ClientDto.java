package br.com.henrique.mineradora.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Jacksonized
@AllArgsConstructor
@Builder
@Data
public class ClientDto {
    private UUID id;
    private String name;
    private String uf;
}
