package com.flagpvp.json;

import com.flagpvp.game.domain.entity.Regions;
import com.flagpvp.sovereignstate.domain.entity.SovereignState;

import java.io.IOException;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

// TODO: Change 'null' when declaring a new instance of a SovereignState object.
// TODO: Update JSON content to have the flag image URL of each sovereign state.
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
            new SovereignState("China", Regions.ASIA, null), 
            new SovereignState("Mexico", Regions.AMERICAS, null),
            new SovereignState("Iceland", Regions.EUROPE, null),
            new SovereignState("Ethiopia", Regions.AFRICA, null),
            new SovereignState("Australia", Regions.OCEANIA, null)
        );
    }

    @Test
    void sovereignStateSerializationTest() throws IOException {

        // The sovereign state to be serialized.
        SovereignState sovereignState = new SovereignState("Laos", Regions.ASIA, null); 

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
            assertThat(json.parse(expected)).isEqualTo(new SovereignState("Laos", Regions.ASIA, null));
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