package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CIUDAD")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class City {
    @Id
    @Column(name = "ID_CIUDAD")
    private Long cityId;

    @Column(name = "NOMBRE_CIUDAD")
    private String cityName;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Column(name = "CURRENT")
    private String current;

    @Column(name = "TIMEZONE")
    private String timeZone;

    @Column(name = "FORECAST_DAYS")
    private Integer forecastDays;
}
