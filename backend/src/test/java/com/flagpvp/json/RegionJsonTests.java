package com.flagpvp.json;

import com.flagpvp.game.domain.entity.Region;
import com.flagpvp.game.domain.entity.Regions;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.assertj.core.util.Arrays;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.beans.factory.annotation.Autowired;

@JsonTest
public class RegionJsonTests {

    @Autowired
    private JacksonTester<Region> json;

    @Autowired
    private JacksonTester<Region[]> jsonList;

    private Region[] regions;

    @BeforeEach
    void setUp() {
        regions = Arrays.array(
            new Region(Regions.AMERICAS, 35),
            new Region(Regions.EUROPE, 44),
            new Region(Regions.AFRICA, 54),
            new Region(Regions.ASIA, 48),
            new Region(Regions.OCEANIA, 14)
        );
    }

    @Test
    void regionSerializationTest() throws IOException {

        // The region to be serialized.
        Region region = new Region(Regions.AMERICAS, 35);          

        // Checks that the region serializes to a JSON content correctly.
        assertThat(json.write(region)).isStrictlyEqualToJson("region_test.json");
        assertThat(json.write(region)).hasJsonPathStringValue("@.name");
        assertThat(json.write(region)).extractingJsonPathStringValue("@.name", Regions.AMERICAS);
        assertThat(json.write(region)).hasJsonPathNumberValue("@.statesCount");
        assertThat(json.write(region)).extractingJsonPathNumberValue("@.statesCount", 35);
    }

    @Test
    void regionsDeserializationTest() throws IOException {

        // The expected content of the JSON file.
        String expected = """
            {
                "name": "AMERICAS",
                "statesCount": 35
            }
            """;

        // Checks that the expected content of the JSON file deserializes to a Region object.
        assertThat(json.parseObject(expected)).isEqualTo(new Region(Regions.AMERICAS, 35));
        assertThat(json.parseObject(expected).name()).isEqualTo(Regions.AMERICAS);
        assertThat(json.parseObject(expected).statesCount()).isEqualTo(35);
    }

    @Test
    void regionsListSerializationTest() throws IOException {

        // Checks that the list of Region objects serialilzes to a JSON content correctly.
        assertThat(jsonList.write(regions)).isStrictlyEqualToJson("region_list_test.json");

    }

    void regionsListDeserializationTest() throws IOException {
        String expected = """
            [
                {
                    "name": "AMERICAS",
                    "statesCount": 35
                },

                {
                    "name": "EUROPE",
                    "statesCount": 44
                },

                {
                    "name": "AFRICA",
                    "statesCount": 54
                },

                {
                    "name": "ASIA",
                    "statesCount": 48
                },

                {
                    "name": "OCEANIA",
                    "statesCount": 14
                }
            ] 
            """;
        
        // Checks that the expected content of the JSON file deserializes to Region objects.
        assertThat(json.parse(expected)).isEqualTo(regions);

    }
}