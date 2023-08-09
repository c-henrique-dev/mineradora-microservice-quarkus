package br.com.henrique.mineradora.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name="opportunity")
@Data
public class OpportunityEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private Date date;

    @Column(name = "proposal_id")
    private UUID proposalId;

    private String customer;

    @Column(name = "price_tonne")
    private BigDecimal priceTonne;

    @Column(name = "last_currency_quotation")
    private BigDecimal lastDollarQuotation;

}
