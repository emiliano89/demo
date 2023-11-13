package com.example.demo.dao;

import com.example.demo.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDao  extends JpaRepository<City, Long> {
    City findByCityName(String cityName);
}
