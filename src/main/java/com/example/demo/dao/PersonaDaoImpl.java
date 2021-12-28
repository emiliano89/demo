package com.example.demo.dao;

import com.example.demo.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.List;

@Repository
public class PersonaDaoImpl implements PersonaDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Persona findById(Long id) {
        return new Persona();
    };

    @Override
    public List<Persona> findAll() {
        String query = "SELECT * FROM PERSONAS";
        List<Persona> personas = jdbcTemplate.query(query, new BeanPropertyRowMapper<Persona>(Persona.class));
        return personas;
    }

    @Override
    public int save(Persona persona) {
        String query = "INSERT INTO PERSONAS (ID, NOMBRE_COMPLETO, IDENTIFICACION, EDAD, GENERO, ESTADO, MANEJA, USA_LENTES, DIABETICO)" +
                " VALUES("+ persona.getId()+",'"+persona.getNombreCompleto()+"', "+ persona.getIdentificacion()+", " +
                persona.getEdad()+", '"+persona.getGenero() +"', "+persona.isEstado()+", "+
                persona.isManeja()+", "+persona.isUsaLentes()+", "+persona.isDiabetico()+" )";
        try {jdbcTemplate.update(query);}
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int update(Persona persona) {
        String query = "UPDATE PERSONAS " +
                " SET NOMBRE_COMPLETO = '"+ persona.getNombreCompleto() +
                "', IDENTIFICACION = "+ persona.getIdentificacion() +
                ", EDAD =" + persona.getEdad() +
                ", GENERO = '"+ persona.getGenero()+
                "', ESTADO = " + persona.isEstado()+
                ", MANEJA = "+ persona.isManeja()+
                ", USA_LENTES = "+persona.isUsaLentes()+
                ", DIABETICO = "+ persona.isDiabetico()+
                " WHERE ID = "+ persona.getId();
        try {jdbcTemplate.update(query);}
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public void delete(Long id) {
        jdbcTemplate.execute("DELETE FROM PERSONAS WHERE ID = "+ id);
    }
}
