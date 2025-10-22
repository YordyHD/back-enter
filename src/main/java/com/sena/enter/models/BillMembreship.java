package com.sena.enter.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill_membreship")
public class BillMembreship {
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
}
