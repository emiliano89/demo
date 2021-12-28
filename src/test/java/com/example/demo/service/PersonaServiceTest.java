package com.example.demo.service;

import com.example.demo.dao.OtraEnfermedadDao;
import com.example.demo.dao.PersonaDao;
import com.example.demo.dto.PersonaDto;
import com.example.demo.entities.OtraEnfermedad;
import com.example.demo.entities.Persona;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PersonaServiceTest {

    @Mock
    private PersonaDao personaDao;

    @Mock
    private OtraEnfermedadDao otraEnfermedadDao;

    @InjectMocks
    private PersonaService personaService;

    @Test
    void findAll() {
        List<Persona> personaList = new ArrayList<>();
        List<OtraEnfermedad> otraEnfermedadList = new ArrayList<>();
        OtraEnfermedad otraEnfermedad = Mockito.mock(OtraEnfermedad.class);
        Persona persona = Mockito.mock(Persona.class);
        Mockito.when(persona.getId()).thenReturn(1L);
        personaList.add(persona);
        otraEnfermedadList.add(otraEnfermedad);
        Mockito.when(personaDao.findAll()).thenReturn(personaList);
        Mockito.when(otraEnfermedadDao.findByPersona(Mockito.anyLong())).thenReturn(otraEnfermedadList);

        List<PersonaDto> personaDtoList = personaService.findAll();

        assertFalse(personaDtoList.isEmpty());
        assertEquals(1, personaDtoList.size());
        assertEquals(persona, personaDtoList.get(0).getPersona());
        assertEquals(otraEnfermedadList, personaDtoList.get(0).getOtraEnfermedadList());
    }

    @Test
    void searchPersona() {
        Persona persona = Mockito.mock(Persona.class);
        Mockito.when(persona.getId()).thenReturn(1L);
        List<OtraEnfermedad> otraEnfermedadList = new ArrayList<>();
        OtraEnfermedad otraEnfermedad = Mockito.mock(OtraEnfermedad.class);
        otraEnfermedadList.add(otraEnfermedad);
        Mockito.when(personaDao.findById(Mockito.anyLong())).thenReturn(persona);
        Mockito.when(otraEnfermedadDao.findByPersona(persona.getId())).thenReturn(otraEnfermedadList);

        PersonaDto personaDto = personaService.searchPersona(1L);

        assertEquals(1L, personaDto.getPersona().getId());
        assertEquals(persona, personaDto.getPersona());
        assertEquals(otraEnfermedadList.get(0), personaDto.getOtraEnfermedadList().get(0));
        assertEquals(otraEnfermedadList.size(), personaDto.getOtraEnfermedadList().size());
    }

    @Test
    void savePersona() {
        PersonaDto dto = new PersonaDto();
        Persona persona = Mockito.mock(Persona.class);
        Mockito.when(persona.getId()).thenReturn(1L);
        List<OtraEnfermedad> otraEnfermedadList = new ArrayList<>();
        OtraEnfermedad otraEnfermedad = Mockito.mock(OtraEnfermedad.class);
        otraEnfermedadList.add(otraEnfermedad);
        dto.setPersona(persona);
        dto.setOtraEnfermedadList(otraEnfermedadList);
        Mockito.when(personaDao.save(Mockito.any())).thenReturn(1);
        Mockito.when(otraEnfermedadDao.save(Mockito.any())).thenReturn(1);

        boolean result = personaService.savePersona(dto);

        assertTrue(result);
        assertFalse(dto.getOtraEnfermedadList().isEmpty());
    }

    @Test
    void updatePersona() {
        PersonaDto dto = new PersonaDto();
        Persona persona = Mockito.mock(Persona.class);
        Mockito.when(persona.getId()).thenReturn(1L);
        List<OtraEnfermedad> otraEnfermedadList = new ArrayList<>();
        OtraEnfermedad otraEnfermedad = Mockito.mock(OtraEnfermedad.class);
        otraEnfermedadList.add(otraEnfermedad);
        dto.setPersona(persona);
        dto.setOtraEnfermedadList(otraEnfermedadList);
        Mockito.when(personaDao.update(Mockito.any())).thenReturn(1);
        Mockito.when(otraEnfermedadDao.update(Mockito.any())).thenReturn(1);

        boolean result = personaService.updatePersona(dto);

        assertTrue(result);
        assertFalse(dto.getOtraEnfermedadList().isEmpty());
    }
}
