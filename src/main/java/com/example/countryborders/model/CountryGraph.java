package com.example.countryborders.model;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.BFSShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.Multigraph;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CountryGraph {
    private Map<String, Country> countryMap;
    private Graph<Country, DefaultEdge> graph;

    public CountryGraph(Map<String, Country> countryMap) {
        this.countryMap = countryMap;
        this.graph = buildCountryGraph(countryMap);
    }

    public Country getCountry(String code) {
        return this.countryMap.get(code);
    }
    public List<String> findRoute(Country orig, Country dest) {
        BFSShortestPath<Country, DefaultEdge> bfsShortestPath = new BFSShortestPath<>(this.graph);
        GraphPath<Country, DefaultEdge> graphPath = bfsShortestPath.getPath(orig, dest);

        return graphPath.getVertexList()
                .stream()
                .map(Country::getCode)
                .collect(Collectors.toList());
    }

    private Graph<Country, DefaultEdge> buildCountryGraph(Map<String, Country> countryMap) {
        Graph<Country, DefaultEdge> g = new Multigraph<>(DefaultEdge.class);
        countryMap.values().forEach(g::addVertex);
        countryMap.forEach((countryCode, country) -> {
            List<String> borders = country.getBorders();
            if (!borders.isEmpty()) {
                borders.forEach(borderCountry -> g.addEdge(country, countryMap.get(borderCountry)));
            }
        });

        return g;
    }
}
