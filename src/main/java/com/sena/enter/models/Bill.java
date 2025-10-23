package com.sena.enter.models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table (name = "bill")
public class Bill implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "purchase_date")
    private LocalDate purchaseDate;

    @Column(name = "yeard")
    private Integer yeard;

    @Column(name = "number_bill")
    private Long numberBill;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bill")
    @JsonIgnoreProperties(value = { "bill", "membreship" }, allowSetters = true)
    private Set<BillMembreship> billMembreships = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties(value = { "user", "bills", "views", "filmGenres", "documentType", "sex", "cities" }, allowSetters = true)
    private Customer customer;
}

