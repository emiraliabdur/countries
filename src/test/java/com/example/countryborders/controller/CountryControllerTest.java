package com.example.countryborders.controller;

import com.example.countryborders.service.CountryProviderService;
import com.example.countryborders.service.CountryRouteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CountryProviderService countryProviderService;
    @Autowired
    private CountryRouteService countryRouteService;

    @Test
    void shouldFindRouteBetweenNeighbourCountries() throws Exception {
        // UKRAINE -> POLAND
        mockMvc.perform(get("/routing/UKR/POL")
                .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routes", hasItems("UKR", "POL")));
    }

    @Test
    void shouldFindRouteBetweenNotNeighbourCountriesOnSameContinent() throws Exception {
        // CZECHIA -> ITALY
        mockMvc.perform(get("/routing/CZE/ITA")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routes", hasItems("CZE", "AUT", "ITA")));
    }

    @Test
    void shouldFindRouteBetweenNotNeighbourCountriesOnDifferentContinentS() throws Exception {
        // KAZAKHSTAN -> ITALY
        mockMvc.perform(get("/routing/KAZ/ITA")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.routes", hasItems("KAZ","RUS","POL","CZE","AUT","ITA")));
    }

    @Test
    void shouldNotFindCountry() throws Exception {
        mockMvc.perform(get("/routing/PTN/PNH")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotFindRouteFromIsolatedCountry() throws Exception {
        // AUSTRALIA -> EGYPT
        mockMvc.perform(get("/routing/AUS/EGY")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotFindRouteToIsolatedCountry() throws Exception {
        // CHINA -> AUSTRALIA
        mockMvc.perform(get("/routing/CHI/AUS")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldNotFindRouteForDifferentContinents() throws Exception {
        // EGYPT -> USA
        mockMvc.perform(get("/routing/EGY/USA")
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
}