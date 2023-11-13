package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "OTRAS_ENFERMEDADES")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OtherDisease implements Serializable {

    @Id
    @Column(name = "OTRA_ENFERMEDAD_ID")
    private Long otherDiseaseId;

    @Column(name = "PERSONA_ID")
    private Long personId;

    @Column(name = "OTRA_ENFERMEDAD")
    private Boolean otherDisease;

    @Column(name = "DESCRIPCION")
    private String description;
}
