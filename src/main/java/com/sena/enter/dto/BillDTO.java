package com.sena.enter.dto;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BillDTO implements Serializable{

        private Long id;

    private LocalDate purchaseDate;

    private Integer yeard;

    private Long numberBill;

    @NotNull
    private CustomerDTO customer;
}
