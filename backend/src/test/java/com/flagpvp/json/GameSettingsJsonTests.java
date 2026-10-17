package com.flagpvp.json;

import com.flagpvp.dto.GameSettingsDto;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

@JsonTest 
public class GameSettingsJsonTests {

    @Autowired 
    JacksonTester<GameSettingsDto> json;

    @Test 
    public void gameSettingsSerializationTest() throws IOException {
       GameSettingsDto gameSettingsDto = new GameSettingsDto(false, true, true, true, false);

       // Check that the DTO serializes to a JSON content correctly. 
        assertThat(json.write(gameSettingsDto)).isStrictlyEqualToJson("game_settings_test.json");

       assertThat(json.write(gameSettingsDto)).hasJsonPathBooleanValue("@.americas");
       assertThat(json.write(gameSettingsDto)).extractingJsonPathBooleanValue("@.americas", false);

       assertThat(json.write(gameSettingsDto)).hasJsonPathBooleanValue("@.europe", true);
       assertThat(json.write(gameSettingsDto)).extractingJsonPathBooleanValue("@.europe", true);

       assertThat(json.write(gameSettingsDto)).hasJsonPathBooleanValue("@.africa", true);
       assertThat(json.write(gameSettingsDto)).extractingJsonPathBooleanValue("@.africa", true);

       assertThat(json.write(gameSettingsDto)).hasJsonPathBooleanValue("@.asia", true);
       assertThat(json.write(gameSettingsDto)).extractingJsonPathBooleanValue("@.asia", true);

       assertThat(json.write(gameSettingsDto)).hasJsonPathBooleanValue("@.oceania", false);
       assertThat(json.write(gameSettingsDto)).extractingJsonPathBooleanValue("@.oceania", false);
    }

    @Test
    public void gameSettingsDeserializationTest() throws IOException {

        // String of JSON content.
        String jsonContent = """
            { 
                "americas": false,
                "africa": true,
                "europe": false,
                "oceania": false,
                "asia": false
            } 
        """;

        // Parse JSON content to a GameSettingsDto object.
        assertThat(json.parseObject(jsonContent)).isEqualTo(new GameSettingsDto(false, false, true, false, false));
    }
}
