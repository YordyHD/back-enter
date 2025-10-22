package com.sena.enter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


@Entity
@Table(name = "membreship")
public class Membreship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "membership_name", length = 255, nullable = false)
    private String membershipName;

    @Column(name = "price", precision = 21, scale = 2)
    private BigDecimal price;

    @Column(name = "duration")
    private Integer duration;

    @Lob
    @Column(name = "imagen")
    private byte[] imagen;

    @Column(name = "imagen_content_type")
    private String imagenContentType;

    @Size(max = 255)
    @Column(name = "description", length = 255)
    private String description;
}
