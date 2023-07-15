package com.example.countryborders.service.impl;

import com.example.countryborders.exception.CountryNotFoundException;
import com.example.countryborders.exception.RouteNotFoundException;
import com.example.countryborders.model.Country;
import com.example.countryborders.model.CountryGraph;
import com.example.countryborders.model.Route;
import com.example.countryborders.service.CountryProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static com.example.countryborders.model.Region.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CountryCountryRouteServiceImplTest {

    @InjectMocks
    private CountryRouteServiceImpl countryRouteService;

    @Mock
    private CountryProviderService countryProviderService;

    private static CountryGraph GRAPH = buildGraph();

    @BeforeEach
    public void setUp() {
        when(countryProviderService.obtainCountryGraph()).thenReturn(buildGraph());
    }

    @Test
    void shouldThrowExceptionWithInvalidOriginCountry() {
        CountryNotFoundException thrown = assertThrows(
                CountryNotFoundException.class,
                () -> countryRouteService.findRouteViaBorders("02", "A")
        );
        assertTrue(thrown.getMessage().contains("Country with code 02 is missing"));
    }

    @Test
    void shouldThrowExceptionWithInvalidDestinationCountry() {
        CountryNotFoundException thrown = assertThrows(
                CountryNotFoundException.class,
                () -> countryRouteService.findRouteViaBorders("A", "01")
        );
        assertTrue(thrown.getMessage().contains("Country with code 01 is missing"));
    }

    @Test
    void shouldThrowExceptionWithNotConnectedRegions() {
        RouteNotFoundException thrown = assertThrows(
                RouteNotFoundException.class,
                () -> countryRouteService.findRouteViaBorders("A", "H")
        );
        assertTrue(thrown.getMessage().contains("Route from country A to H is not found"));
    }

    @Test
    void shouldThrowExceptionForCountryWithEmptyBorders() {
        RouteNotFoundException thrown = assertThrows(
                RouteNotFoundException.class,
                () -> countryRouteService.findRouteViaBorders("J", "H")
        );
        assertTrue(thrown.getMessage().contains("Route from country J to H is not found"));
    }

    @ParameterizedTest
    @MethodSource("inputCountriesAndExpectedRouteParameterProvider")
    void shouldFindRoutes(String origin, String destination, List<String> expectedRoutes) {
        Route route = countryRouteService.findRouteViaBorders(origin, destination);
        assertEquals(expectedRoutes, route.getRoutes());
    }

    private static Stream<Arguments> inputCountriesAndExpectedRouteParameterProvider() {
        return Stream.of(
                Arguments.of("A", "B", List.of("A", "B")),
                Arguments.of("B", "C", List.of("B", "A", "C")),
                Arguments.of("B", "D", List.of("B", "A", "C", "D")),
                Arguments.of("E", "F", List.of("E", "F"))
        );
    }

    private static CountryGraph buildGraph() {
        var countryMap = Map.of(
                "A", new Country("A", EUROPE, List.of("B", "C")),
                "B", new Country("B", EUROPE, List.of("A")),
                "C", new Country("C", EUROPE, List.of("A", "D")),
                "D", new Country("D", ASIA, List.of("E", "F", "C")),
                "E", new Country("E", ASIA, List.of("D")),
                "F", new Country("F", ASIA, List.of("E")),
                "G", new Country("G", AMERICAS, List.of("H")),
                "H", new Country("H", AMERICAS, List.of("G")),
                "J", new Country("J", AMERICAS, List.of())
        );

        return new CountryGraph(countryMap);
    }
}
