package com.sena.enter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "director")
public class Director implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "name_director", length = 30)
    private String nDirector;

    @Size(max = 30)
    @Column(name = "las_name_director", length = 30)
    private String lNDirector;

    @Lob
    @Column(name = "picture")
    private byte[] pic;

    @Column(name = "picture_content_type")
    private String picContentType;

    @Column(name = "yearbirth")
    private LocalDate year;
}
