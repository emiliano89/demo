package com.example.demo.externals;

import com.example.demo.dao.CityDao;
import com.example.demo.dto.WeatherResponseDto;
import com.example.demo.dto.WeatherServiceResponseDto;
import com.example.demo.entities.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class WeatherService {

    @Value("${url.weather}")
    private String url;

    @Autowired
    private CityDao cityDao;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherService(String url) {
        this.url = url;
    }

    public WeatherResponseDto weatherResponse(City city) {
        WeatherServiceResponseDto responseDto;
        WeatherResponseDto response = new WeatherResponseDto();
        String uri = url + "v1/forecast?latitude=" +
                city.getLatitude() + "&longitude=" + city.getLongitude() + "&current=" + city.getCurrent() +
                "&timezone=" + city.getTimeZone() + "&forecast_days=" + 1;
        try {
            responseDto = restTemplate.getForObject(uri, WeatherServiceResponseDto.class);
            if (responseDto != null) {
                response.setCity(city.getCityName());
                response.setTemperature(responseDto.getCurrent().getTemperature2m().toString());
            }
        } catch (Exception e) {
            log.error("Error al obtener la temperatura, {}", e.getMessage());
            response = null;
        }
        return response;
    }

    @Scheduled(fixedDelayString = "PT30S", initialDelay = 100)
    @SchedulerLock(name = "TaskScheduler_scheduledTask",
            lockAtLeastForString = "30",
            lockAtMostForString = "300")
    public void getWeather() {
        // Lógica para obtener el clima y actualizarlo cada 30 segundos
        System.out.println("Obteniendo información del clima...");
        List<City> cityList = cityDao.findAll();
        for(City city : cityList) {
            WeatherResponseDto responseDto = weatherResponse(city);
            city.setTemp(responseDto.getTemperature());
            cityDao.save(city);
        }

    }
}
