package com.example.countryborders.client.mledoze;

import com.example.countryborders.client.mledoze.dto.CountryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@FeignClient(name = "countries", url = "${client.mledoze.url}")
public interface CountryClient {

    @RequestMapping(method = GET, value = "/countries/master/countries.json",
            consumes = "application/json", produces = "application/json")
    List<CountryDto> getCountries();
}
