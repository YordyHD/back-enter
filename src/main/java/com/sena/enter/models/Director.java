package com.sena.enter.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "director")
public class Director implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Size(max = 30)
    @Column(name = "name_director", length = 30)
    private String nameDirector;

    @Size(max = 30)
    @Column(name = "las_name_director", length = 30)
    private String lasNameDirector;

    @Lob
    @Column(name = "picture")
    private byte[] picture;

    @Column(name = "picture_content_type")
    private String pictureContentType;

    @Column(name = "yearbirth")
    private LocalDate yearbirth;
}
