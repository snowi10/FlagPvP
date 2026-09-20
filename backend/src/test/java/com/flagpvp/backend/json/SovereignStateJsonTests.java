package com.flagpvp.backend.json;

import com.flagpvp.backend.game.domain.entity.SovereignState;
import com.flagpvp.backend.game.domain.entity.Regions;

import java.io.IOException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

@JsonTest
public class SovereignStateJsonTests {

    @Autowired
    private JacksonTester<SovereignState> json;

    @Autowired
    private JacksonTester<SovereignState[]> jsonList;

    private SovereignState[] sovereignStates;

    @BeforeEach
    void setUp() {
        sovereignStates = Arrays.array(
            new SovereignState("China", Regions.ASIA), 
            new SovereignState("Mexico", Regions.AMERICAS),
            new SovereignState("Iceland", Regions.EUROPE),
            new SovereignState("Ethiopia", Regions.AFRICA),
            new SovereignState("Australia", Regions.OCEANIA)
        );
    }

    @Test
    void sovereignStateSerializationTest() throws IOException {

        // The sovereign state to be serialized.
        SovereignState sovereignState = new SovereignState("Laos", Regions.ASIA); 

        // Checks that the sovereign state serializes to a JSON content correctly.
        assertThat(json.write(sovereignState)).isStrictlyEqualToJson("sovereign_state_test.json");
        assertThat(json.write(sovereignState)).hasJsonPathStringValue("@.name");
        assertThat(json.write(sovereignState)).extractingJsonPathStringValue("@.name", "Laos");
        assertThat(json.write(sovereignState)).hasJsonPathStringValue("@.region");
        assertThat(json.write(sovereignState)).extractingJsonPathStringValue("@.region", Regions.ASIA);
    }

    @Test
    void sovereignStateDeserializationTest() throws IOException {

        // The expected content of the JSON file.
        String expected = """
            {
                "name": "Laos",
                "region": "ASIA"
                
            } 
            """;

            // Checks that the expected content of the JSON file deserializes to a Sovereign State object.
            assertThat(json.parse(expected)).isEqualTo(new SovereignState("Laos", Regions.ASIA));
            assertThat(json.parseObject(expected).name()).isEqualTo("Laos");
            assertThat(json.parseObject(expected).region()).isEqualTo(Regions.ASIA);
    }

    @Test
    void sovereignStateListSerializationTest() throws IOException {

        // Checks that the array of Sovereign State objects serializes to a JSON content correctly.
        assertThat(jsonList.write(sovereignStates)).isStrictlyEqualToJson("sovereign_states_test.json");             

    }

    @Test
    void sovereignStateListDeserialization() throws IOException {

        // The expected content of the JSON file.
        String expected = """
            [
                {
                    "name": "China",
                    "region": "ASIA"
                },

                {
                    "name": "Mexico",
                    "region": "AMERICAS"
                },

                {
                    "name": "Iceland",
                    "region": "EUROPE"
                },

                {
                    "name": "Ethiopia",
                    "region": "AFRICA"
                },

                {
                    "name": "Australia",
                    "region": "OCEANIA"
                }
            ]
        """; 

        // Checks that the expected content of the JSON file deserializes to Sovereign State objects.
        assertThat(jsonList.parse(expected)).isEqualTo(sovereignStates);
    }
}