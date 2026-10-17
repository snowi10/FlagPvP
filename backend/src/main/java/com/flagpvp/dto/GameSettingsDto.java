package com.flagpvp.dto;

import org.springframework.boot.context.properties.bind.DefaultValue;

// DTO for the game settings.
// Players specify the settings for their game.
public record GameSettingsDto(
    @DefaultValue("false") boolean americas,
    @DefaultValue("false") boolean europe,
    @DefaultValue("false") boolean africa,
    @DefaultValue("false") boolean asia,
    @DefaultValue("false") boolean oceania

    // TODO: Add more setting options.
) {
    // Getters.
    public boolean getAmericas() {
        return americas;
    }
    public boolean getEurope() {
        return europe;
    }
    public boolean getAfrica() {
        return africa;
    }
    public boolean getAsia() {
        return asia;
    }
    public boolean getOceania() {
        return oceania;
    }
}
