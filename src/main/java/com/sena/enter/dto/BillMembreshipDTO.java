package com.sena.enter.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class BillMembreshipDTO implements Serializable{

    @NotNull(message = "El ID de la factura (bill) no puede ser nulo")
    private Long billId;
    private Long numberBill; 

    @NotNull(message = "El ID de la membresía no puede ser nulo")
    private Long membreshipId;
    private String membershipName; 

    @NotNull(message = "La fecha inicial es obligatoria")
    private LocalDate inicialDate;

    @NotNull(message = "La fecha final es obligatoria")
    private LocalDate finalDate;

    private BigDecimal salePrice;

    @Size(max = 500, message = "Las observaciones no pueden superar los 500 caracteres")
    private String observaciones;

    @Column(nullable = false)
    private String estado; 

    @Column(nullable = false, unique = true)
    private String usuario; 
    @Column(nullable = false)
    private String clave;
}
