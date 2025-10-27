package com.sena.enter.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bill_membreship")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillMembreship implements Serializable {

    @EmbeddedId
    private BillMembreshipId id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("billId")
    @JoinColumn(name = "bill_id")
    @JsonIgnoreProperties(value = { "billMembreships", "customer" }, allowSetters = true)
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("membreshipId")
    @JoinColumn(name = "membreship_id")
    @JsonIgnoreProperties(value = { "billMembreships" }, allowSetters = true)
    private Membreship membreship;

    @NotNull
    @Column(name = "final_date")
    private LocalDate fiDate;

    @Column(name = "inicial_date")
    private LocalDate iniDate;

    @Column(name = "sale_price", precision = 21, scale = 2)
    private BigDecimal saleP;
}
