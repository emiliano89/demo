package com.example.demo.dao;

import com.example.demo.entities.OtraEnfermedad;

import java.util.List;

public interface OtraEnfermedadDao {

    List<OtraEnfermedad> findByPersona(Long idPersona);

    List<OtraEnfermedad> findAll();

    int save(OtraEnfermedad otraEnfermedad);

    int update (OtraEnfermedad otraEnfermedad);

    void delete (Long idPersona);
}
