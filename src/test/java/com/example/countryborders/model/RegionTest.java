package com.example.countryborders.model;

import org.junit.jupiter.api.Test;

import static com.example.countryborders.model.Region.*;
import static org.junit.jupiter.api.Assertions.*;

class RegionTest {

    @Test
    void shouldBeConnectedByLandWith() {
        assertTrue(ASIA.connectedByLandWith(EUROPE));
        assertTrue(AFRICA.connectedByLandWith(EUROPE));
        assertTrue(AFRICA.connectedByLandWith(ASIA));
    }

    @Test
    void shouldNotBeConnectedByLandWith() {
        assertFalse(OCEANIA.connectedByLandWith(EUROPE));
        assertFalse(ANTARCTIC.connectedByLandWith(AMERICAS));
        assertFalse(ANTARCTIC.connectedByLandWith(AFRICA));
        assertFalse(AMERICAS.connectedByLandWith(OCEANIA));
    }

}