package com.example.demo.dao;

import com.example.demo.entities.Persona;

import java.util.List;

public interface PersonaDao {

    Persona findById(Long id);

    List<Persona> findAll();

    int save(Persona persona);

    int update (Persona persona);

    void delete (Long id);
}
