package com.flagpvp.game.controller;

import com.flagpvp.game.domain.entity.Regions;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestRestTemplate
public class SovereignStateControllerTests {

    @Autowired
    TestRestTemplate restTemplate;
    
    void shouldReturnASovereignState() {

        // Gets 'Ethiopia' from the SovereignStateController GetMapping method.
        ResponseEntity<String> response = restTemplate.getForEntity("/sovereignState/Ethiopia", String.class);

        // Checks that the Sovereign State object was retrieved.
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Parses the Sovereign State object to a JSON content.
        DocumentContext documentContext = JsonPath.parse(response.getBody());

        // Checks that the JSON content has the correct information.        
        String name = documentContext.read("$.name");
        assertThat(name).isEqualTo("Ethiopia");
        Regions region = documentContext.read("$.region");
        assertThat(region).isEqualTo(Regions.AFRICA);
    }
}
