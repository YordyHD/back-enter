package com.sena.enter.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class BillMembreshipDTO implements Serializable{

        private Long id;

    private LocalDate finalDate;

    private LocalDate inicialDate;

    private BigDecimal salePrice;

    @NotNull
    private BillDTO bill;

    @NotNull
    private MembreshipDTO membreship;
}
