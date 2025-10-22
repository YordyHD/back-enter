package com.sena.enter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "document_number", length = 50, nullable = false)
    private String documentNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;

    @Size(max = 50)
    @Column(name = "second_name", length = 50)
    private String secondName;

    @NotNull
    @Size(max = 50)
    @Column(name = "first_las_name", length = 50, nullable = false)
    private String firstLasName;

    @Size(max = 50)
    @Column(name = "second_last_name", length = 50)
    private String secondLastName;
}
