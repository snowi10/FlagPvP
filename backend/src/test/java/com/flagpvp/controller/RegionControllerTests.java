package com.flagpvp.controller;

import com.flagpvp.domain.Regions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import org.springframework.http.*;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace=Replace.NONE)
@AutoConfigureTestRestTemplate 
public class RegionControllerTests {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void shouldGetAllRegions() {

        // Gets all Regions from the RegionController GetMapping method. 
        ResponseEntity<String> americasRes = restTemplate.getForEntity("/region/AMERICAS", String.class);
        ResponseEntity<String> europeRes = restTemplate.getForEntity("/region/EUROPE", String.class);
        ResponseEntity<String> africaRes = restTemplate.getForEntity("/region/AFRICA", String.class);
        ResponseEntity<String> asiaRes = restTemplate.getForEntity("/region/ASIA", String.class);
        ResponseEntity<String> oceaniaRes = restTemplate.getForEntity("/region/OCEANIA", String.class);

        // Checks that the Region objects were retrieved. 
        assertThat(americasRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(europeRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(africaRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(asiaRes.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(oceaniaRes.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Parses the Region objects to JSON contents.
        DocumentContext americasDocContext = JsonPath.parse(americasRes.getBody()); 
        DocumentContext europeDocContext = JsonPath.parse(europeRes.getBody());
        DocumentContext africaDocContext = JsonPath.parse(africaRes.getBody());
        DocumentContext asiaDocContext = JsonPath.parse(asiaRes.getBody());
        DocumentContext oceaniaDocContext = JsonPath.parse(oceaniaRes.getBody());

        // Checks that the JSON contents have the correct information.
        String americasName = americasDocContext.read("$.name");         
        String europeName = europeDocContext.read("$.name");
        String africaName = africaDocContext.read("$.name");
        String asiaName = asiaDocContext.read("$.name");
        String oceaniaName = oceaniaDocContext.read("$.name");

        Integer americasCount = americasDocContext.read("$.statesCount");
        Integer europeCount = europeDocContext.read("$.statesCount");
        Integer africaCount = africaDocContext.read("$.statesCount");
        Integer asiaCount = asiaDocContext.read("$.statesCount");
        Integer oceaniaCount = oceaniaDocContext.read("$.statesCount");

        assertThat(americasName).isEqualTo(Regions.AMERICAS.toString());
        assertThat(europeName).isEqualTo(Regions.EUROPE.toString());
        assertThat(africaName).isEqualTo(Regions.AFRICA.toString());
        assertThat(asiaName).isEqualTo(Regions.ASIA.toString());
        assertThat(oceaniaName).isEqualTo(Regions.OCEANIA.toString());

        assertThat(americasCount).isEqualTo(35);
        assertThat(europeCount).isEqualTo(44);
        assertThat(africaCount).isEqualTo(54);
        assertThat(asiaCount).isEqualTo(48);
        assertThat(oceaniaCount).isEqualTo(14);
    }

    @Test
    public void shouldNotGetARegionThatDoesNotExist() {

        // Gets a Region object that does not exist and checks for an error.
        ResponseEntity<String> doesNotExist = restTemplate.getForEntity("/region/DoesNotExist", String.class);
        assertThat(doesNotExist.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(doesNotExist.getBody()).isNotBlank();
    }
}
