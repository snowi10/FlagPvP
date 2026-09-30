package com.flagpvp.json;

import com.flagpvp.domain.Regions;
import com.flagpvp.domain.SovereignState;

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
            new SovereignState("China", Regions.ASIA, "https://upload.wikimedia.org/wikipedia/commons/f/fa/Flag_of_the_People%27s_Republic_of_China.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
), 
            new SovereignState("Mexico", Regions.AMERICAS, "https://upload.wikimedia.org/wikipedia/commons/f/fc/Flag_of_Mexico.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
),
            new SovereignState("Iceland", Regions.EUROPE, "https://upload.wikimedia.org/wikipedia/commons/c/ce/Flag_of_Iceland.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
),
            new SovereignState("Ethiopia", Regions.AFRICA, "https://upload.wikimedia.org/wikipedia/commons/7/71/Flag_of_Ethiopia.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
),
            new SovereignState("Australia", Regions.OCEANIA, "https://upload.wikimedia.org/wikipedia/commons/8/88/Flag_of_Australia_%28converted%29.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original")
        );
    }

    @Test
    void sovereignStateSerializationTest() throws IOException {

        // The sovereign state to be serialized.
        SovereignState sovereignState = new SovereignState("Laos", 
                                                           Regions.ASIA, 
                                                           "https://upload.wikimedia.org/wikipedia/commons/5/56/Flag_of_Laos.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"); 

        // Checks that the sovereign state serializes to a JSON content correctly.
        assertThat(json.write(sovereignState)).isStrictlyEqualToJson("sovereign_state_test.json");
        assertThat(json.write(sovereignState)).hasJsonPathStringValue("@.name");
        assertThat(json.write(sovereignState)).extractingJsonPathStringValue("@.name", "Laos");
        assertThat(json.write(sovereignState)).hasJsonPathStringValue("@.region");
        assertThat(json.write(sovereignState)).extractingJsonPathStringValue("@.region", Regions.ASIA);
        assertThat(json.write(sovereignState)).hasJsonPathStringValue("@.flagImage");
        assertThat(json.write(sovereignState)).extractingJsonPathStringValue("@.flagImage", "https://upload.wikimedia.org/wikipedia/commons/5/56/Flag_of_Laos.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original");
    }

    @Test
    void sovereignStateDeserializationTest() throws IOException {

        // The expected content of the JSON file.
        String expected = """
            {
                "name": "Laos",
                "region": "ASIA",
                "flagImage": "https://upload.wikimedia.org/wikipedia/commons/5/56/Flag_of_Laos.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original" 
            } 
            """;

            // Checks that the expected content of the JSON file deserializes to a Sovereign State object.
            assertThat(json.parse(expected)).isEqualTo(new SovereignState("Laos", Regions.ASIA, "https://upload.wikimedia.org/wikipedia/commons/5/56/Flag_of_Laos.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"));
            assertThat(json.parseObject(expected).name()).isEqualTo("Laos");
            assertThat(json.parseObject(expected).region()).isEqualTo(Regions.ASIA);
            assertThat(json.parseObject(expected).flagImage()).isEqualTo("https://upload.wikimedia.org/wikipedia/commons/5/56/Flag_of_Laos.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original");
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
                    "region": "ASIA",
                    "flagImage": "https://upload.wikimedia.org/wikipedia/commons/f/fa/Flag_of_the_People%27s_Republic_of_China.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
                },

                {
                    "name": "Mexico",
                    "region": "AMERICAS",
                    "flagImage": "https://upload.wikimedia.org/wikipedia/commons/f/fc/Flag_of_Mexico.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
                },

                {
                    "name": "Iceland",
                    "region": "EUROPE",
                    "flagImage": "https://upload.wikimedia.org/wikipedia/commons/c/ce/Flag_of_Iceland.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
                },

                {
                    "name": "Ethiopia",
                    "region": "AFRICA",
                    "flagImage": "https://upload.wikimedia.org/wikipedia/commons/7/71/Flag_of_Ethiopia.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
                },

                {
                    "name": "Australia",
                    "region": "OCEANIA",
                    "flagImage": "https://upload.wikimedia.org/wikipedia/commons/8/88/Flag_of_Australia_%28converted%29.svg?utm_source=en.wikipedia.org&utm_campaign=imageinfo&utm_content=original"
                }
            ]
        """; 

        // Checks that the expected content of the JSON file deserializes to Sovereign State objects.
        assertThat(jsonList.parse(expected)).isEqualTo(sovereignStates);
    }
}