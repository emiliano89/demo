package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
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
		jdbcTemplate.execute("DROP TABLE CIUDAD IF EXISTS");
		jdbcTemplate.execute("CREATE TABLE PERSONAS(ID INTEGER PRIMARY KEY NOT NULL , " +
				"NOMBRE_COMPLETO VARCHAR(100) , IDENTIFICACION INTEGER , EDAD INTEGER , " +
				"GENERO VARCHAR(1) , ESTADO BIT , MANEJA BIT , USA_LENTES BIT , " +
				"DIABETICO BIT )");
		jdbcTemplate.execute("CREATE TABLE OTRAS_ENFERMEDADES(OTRA_ENFERMEDAD_ID INTEGER PRIMARY KEY NOT NULL , " +
				"PERSONA_ID INTEGER , OTRA_ENFERMEDAD BIT , DESCRIPCION VARCHAR (100))");
		jdbcTemplate.execute("CREATE TABLE CIUDAD(ID_CIUDAD INTEGER PRIMARY KEY NOT NULL , " +
				"NOMBRE_CIUDAD VARCHAR(100) , LATITUDE DECIMAL(8,6), LONGITUDE DECIMAL(9,6), " +
				"CURRENT VARCHAR(50) , TIMEZONE VARCHAR(50), FORECAST_DAYS INTEGER)");
		jdbcTemplate.execute("INSERT INTO CIUDAD(ID_CIUDAD, NOMBRE_CIUDAD, LATITUDE, LONGITUDE, CURRENT, TIMEZONE, FORECAST_DAYS)" +
				" VALUES(1, 'CORDOBA', -31.4135, -64.181, 'temperature_2m', 'auto', 1)");
		jdbcTemplate.execute("INSERT INTO CIUDAD(ID_CIUDAD, NOMBRE_CIUDAD, LATITUDE, LONGITUDE, CURRENT, TIMEZONE, FORECAST_DAYS)" +
				" VALUES(2, 'BUENOS AIRES', -34.6131, -58.3772, 'temperature_2m', 'auto', 1)");
		jdbcTemplate.execute("INSERT INTO CIUDAD(ID_CIUDAD, NOMBRE_CIUDAD, LATITUDE, LONGITUDE, CURRENT, TIMEZONE, FORECAST_DAYS)" +
				" VALUES(3, 'ROSARIO', -32.9468, -60.6393, 'temperature_2m', 'auto', 1)");
	}
}
