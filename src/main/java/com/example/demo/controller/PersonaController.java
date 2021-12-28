package com.example.demo.controller;

import com.example.demo.dto.PersonaDto;
import com.example.demo.entities.Persona;
import com.example.demo.service.PersonaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/persona")
public class PersonaController {

    private static final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private PersonaService personaService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Object> findAll() {
        List<PersonaDto> personas = personaService.findAll();

        return new ResponseEntity<>(personas,
                null, HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getById(@PathVariable("id") Long id) {
        PersonaDto response = personaService.searchPersona(id);
        if(response == null) {
            return new ResponseEntity<>(null,
                    null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> save(@RequestBody PersonaDto dto) {
        boolean response = personaService.savePersona(dto);
        if(!response) {
            return new ResponseEntity<>(null,
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@RequestBody PersonaDto dto) {
        boolean response = personaService.updatePersona(dto);
        if(!response) {
            return new ResponseEntity<>(null,
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(response,
                null, HttpStatus.CREATED);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        try {
            personaService.deletePersona(id);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),
                    null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(null,
                null, HttpStatus.OK);
    }

}
