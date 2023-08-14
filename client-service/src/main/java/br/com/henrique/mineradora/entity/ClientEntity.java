package br.com.henrique.mineradora.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    private String uf;
}
