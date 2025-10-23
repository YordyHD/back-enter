package com.sena.enter.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "bill_membreship")
public class BillMembreship implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "final_date")
    private LocalDate finalDate;

    @Column(name = "inicial_date")
    private LocalDate inicialDate;

    @Column(name = "sale_price", precision = 21, scale = 2)
    private BigDecimal salePrice;
    
    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "billMembreships", "customer" }, allowSetters = true)
    private Bill bill;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "billMembreships" }, allowSetters = true)
    private Membreship membreship;
}
