package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name = "OTRAS_ENFERMEDADES")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OtraEnfermedad implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "OTRA_ENFERMEDAD_ID")
    private Long id;

    @Column(name = "PERSONA_ID")
    private Long personaId;

    @Column(name = "OTRA_ENFERMEDAD")
    private boolean otraEnfermedad;

    @Column(name = "DESCRIPCION")
    private String descripcion;
}
