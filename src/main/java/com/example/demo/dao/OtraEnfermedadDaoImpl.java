package com.example.demo.dao;

import com.example.demo.entities.OtraEnfermedad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OtraEnfermedadDaoImpl implements OtraEnfermedadDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<OtraEnfermedad> findByPersona(Long idPersona) {
        String query = "SELECT * FROM OTRAS_ENFERMEDADES WHERE PERSONA_ID = "+ idPersona;
        List<OtraEnfermedad> otrasEnfermedades = jdbcTemplate.query(query, new BeanPropertyRowMapper<OtraEnfermedad>(OtraEnfermedad.class));
        return otrasEnfermedades;
    }

    @Override
    public List<OtraEnfermedad> findAll() {
        String query = "SELECT * FROM OTRAS_ENFERMEDADES";
        List<OtraEnfermedad> otrasEnfermedades = jdbcTemplate.query(query, new BeanPropertyRowMapper<OtraEnfermedad>(OtraEnfermedad.class));
        return otrasEnfermedades;
    }

    @Override
    public int save(OtraEnfermedad otraEnfermedad) {
        if(!otraEnfermedad.isOtraEnfermedad()) {
            otraEnfermedad.setDescripcion(null);
        }
        String query = "INSERT INTO OTRAS_ENFERMEDADES(OTRA_ENFERMEDAD_ID, PERSONA_ID, OTRA_ENFERMEDAD, DESCRIPCION)" +
                " VALUES("+otraEnfermedad.getId()+", "+ otraEnfermedad.getPersonaId()+", " +
                otraEnfermedad.isOtraEnfermedad()+", '"+otraEnfermedad.getDescripcion() +"' )";
        try {jdbcTemplate.update(query);}
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public int update(OtraEnfermedad otraEnfermedad) {
        if(!otraEnfermedad.isOtraEnfermedad()) {
            otraEnfermedad.setDescripcion(null);
        }
        String query = "UPDATE OTRAS_ENFERMEDADES " +
                "SET OTRA_ENFERMEDAD = "+ otraEnfermedad.isOtraEnfermedad() +
                ", DESCRIPCION = '"+ otraEnfermedad.getDescripcion() +
                "' WHERE PERSONA_ID = "+ otraEnfermedad.getPersonaId() +
                " AND OTRA_ENFERMEDAD_ID = " + otraEnfermedad.getId();
        try {jdbcTemplate.update(query);}
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public void delete(Long idPersona) {
        jdbcTemplate.execute("DELETE FROM OTRAS_ENFERMEDADES WHERE PERSONA_ID = "+ idPersona);
    }
}
