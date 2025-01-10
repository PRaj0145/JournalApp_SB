package com.rootZero.journalApp.service;
import com.rootZero.journalApp.constants.Placeholders;
import com.rootZero.journalApp.api.response.WeatherResponse;
import com.rootZero.journalApp.cache.AppCache;
import com.rootZero.journalApp.constants.Placeholders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WheatherService {

   @Value("${weather.api.key}")
   private static String apiKey;


@Autowired
private RestTemplate restTemplate;

@Autowired
private AppCache appCache;

public WeatherResponse getWheather(String city){
  String finalAPI =  appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace("CITY", city).replace("API_KEY", WheatherService.apiKey);
    ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
    WeatherResponse body = response.getBody();
    return body;
}
}