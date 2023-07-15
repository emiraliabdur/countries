package com.example.countryborders.mapper;

import com.example.countryborders.model.Route;
import com.example.countryborders.model.RouteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface RouteMapper {

    @Mapping(source = "routes", target = "routes")
    RouteDto toDto(Route route);
}
