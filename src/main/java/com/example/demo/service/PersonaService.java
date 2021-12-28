package com.example.demo.service;

import com.example.demo.dao.OtraEnfermedadDao;
import com.example.demo.dao.PersonaDao;
import com.example.demo.dto.PersonaDto;
import com.example.demo.entities.OtraEnfermedad;
import com.example.demo.entities.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private OtraEnfermedadDao otraEnfermedadDao;

    public List<PersonaDto> findAll() {

        List<PersonaDto> personaDtoList = new ArrayList<>();
        List<Persona> personas = personaDao.findAll();

        for(Persona persona : personas) {
            PersonaDto personaDto = new PersonaDto();
            personaDto.setPersona(persona);
            List<OtraEnfermedad> otraEnfermedadList = otraEnfermedadDao.findByPersona(persona.getId());
            personaDto.setOtraEnfermedadList(otraEnfermedadList);
            personaDtoList.add(personaDto);
        }


        return personaDtoList;
    }

    public PersonaDto searchPersona(Long id){
        PersonaDto result = new PersonaDto();
        Persona persona = personaDao.findById(id);
        List<OtraEnfermedad> otraEnfermedadList = otraEnfermedadDao.findByPersona(persona.getId());
        result.setPersona(persona);
        result.setOtraEnfermedadList(otraEnfermedadList);
        return result;
    }

    public boolean savePersona(PersonaDto dto) {
        int isSaved =  personaDao.save(dto.getPersona());
        if(!dto.getOtraEnfermedadList().isEmpty()) {
            dto.getOtraEnfermedadList().forEach(enfermedad -> otraEnfermedadDao.save(enfermedad));
        }
        if(isSaved == 0) {
            return false;
        }
        return true;
    }

    public boolean updatePersona (PersonaDto dto) {
        int isSaved =  personaDao.update(dto.getPersona());
        if (!dto.getOtraEnfermedadList().isEmpty()) {
            dto.getOtraEnfermedadList().forEach(enfermedad -> otraEnfermedadDao.update(enfermedad));
        }

        if(isSaved == 0) {
            return false;
        }
        return true;
    }

    public void deletePersona (Long id) {
        personaDao.delete(id);
        otraEnfermedadDao.delete(id);
    }
}
