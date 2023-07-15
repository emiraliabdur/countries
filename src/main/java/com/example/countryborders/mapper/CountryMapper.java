package com.example.countryborders.mapper;

import com.example.countryborders.client.mledoze.dto.CountryDto;
import com.example.countryborders.model.Country;
import com.example.countryborders.model.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel="spring")
public interface CountryMapper {

    @Mapping(source = "cca3", target = "code")
    Country toModel(CountryDto country);

    List<Country> toModels(List<CountryDto> countries);

    default Region toRegion(String regionStr) {
        return Region.valueOf(regionStr.toUpperCase());
    }
}
