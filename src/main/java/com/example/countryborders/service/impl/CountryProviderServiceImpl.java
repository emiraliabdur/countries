package com.example.countryborders.service.impl;

import com.example.countryborders.client.mledoze.CountryClient;
import com.example.countryborders.mapper.CountryMapper;
import com.example.countryborders.model.Country;
import com.example.countryborders.model.CountryGraph;
import com.example.countryborders.service.CountryProviderService;
import lombok.extern.slf4j.Slf4j;
import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;


@Slf4j
@Service
@CacheConfig(cacheNames={"countryGraph"})
public class CountryProviderServiceImpl implements CountryProviderService {

    private final CountryClient countryClient;
    private final CountryMapper countryMapper;

    public CountryProviderServiceImpl(CountryClient countryClient, CountryMapper countryMapper) {
        this.countryClient = countryClient;
        this.countryMapper = countryMapper;
    }

    @Override
    @Cacheable("countryGraph")
    public CountryGraph obtainCountryGraph() {
        Map<String, Country> countryMap = getCountryMap();

        return new CountryGraph(countryMap);
    }

    private Map<String, Country> getCountryMap() {
        return countryClient.getCountries()
                .stream()
                .map(countryMapper::toModel)
                .collect(Collectors.toMap(Country::getCode, Function.identity()));
    }

    @CacheEvict(value = "countryGraph", allEntries = true)
    @Scheduled(fixedRateString = "${caching.spring.countryGraphTimeToLive}")
    public void emptyCountriesCache() {
        log.info("Evicting country graph cache");
    }


}
