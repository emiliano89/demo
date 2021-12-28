package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		jdbcTemplate.execute("DROP TABLE PERSONAS IF EXISTS");
		jdbcTemplate.execute("DROP TABLE OTRAS_ENFERMEDADES IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE PERSONAS(ID INTEGER PRIMARY KEY NOT NULL, " +
				"NOMBRE_COMPLETO VARCHAR(100), IDENTIFICACION INTEGER, EDAD INTEGER, " +
				"GENERO VARCHAR(1), ESTADO BIT, MANEJA BIT, USA_LENTES BIT, " +
				"DIABETICO BIT)");
		jdbcTemplate.execute("CREATE TABLE OTRAS_ENFERMEDADES(OTRA_ENFERMEDAD_ID INTEGER PRIMARY KEY NOT NULL, " +
				"PERSONA_ID INTEGER, OTRA_ENFERMEDAD BIT, DESCRIPCION VARCHAR (100))");
	}
}
