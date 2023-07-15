<h1 align="center"> Route between 2 countries </h1> <br>

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
## Table of Contents

- [Introduction](#introduction)
- [Technical Stack](#tech-stack)
- [Algorithm](#algorithm)
- [Tests](#tests)
- [How to run the application](#how-to-run-the-application)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction
It's a spring Boot service, that is able to calculate any possible land
route from one country to another. It takes a list of country data in JSON format
and calculate the route by utilizing individual countries border information.

Countries' data source is located under this reference: [Mledoze Countries Source](https://raw.githubusercontent.com/mledoze/countries/master/countries.json)

## Technical Stack
To implement API the following stack was used:
- Java 17
- Spring Boot 3
- Build tool: Maven
- OpenAPI 3.0.1
- Feign Client
- Graph library: org.jgrapht

## Algorithm
Jgrapht library was used to build graph relation between countries.
In order to find path by land between two countries bread-first search is applied:
```shell
  BFSShortestPath<Country, DefaultEdge> bfsShortestPath = new BFSShortestPath<>(this.graph);
  GraphPath<Country, DefaultEdge> graphPath = bfsShortestPath.getPath(orig, dest);
```

## Tests
There are the following tests:
- unit tests to verify graph algorithm: [CountryCountryRouteServiceImplTest.java](src%2Ftest%2Fjava%2Fcom%2Fexample%2Fcountryborders%2Fservice%2Fimpl%2FCountryCountryRouteServiceImplTest.java)
- integration tests to verify API: [CountryControllerTest.java](src%2Ftest%2Fjava%2Fcom%2Fexample%2Fcountryborders%2Fcontroller%2FCountryControllerTest.java)

To run tests:
```shell
mvn clean test
```

## How to run the application
To run the application use the command or your IDE:
```shell
mvn spring-boot:run
```

For verification endpoint run the command with different countries:
```shell
curl --request GET --url http://localhost:8080/routing/CZE/ITA
```
