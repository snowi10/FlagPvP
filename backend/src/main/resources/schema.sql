-- Game and settings that users will play in.
CREATE TABLE Game
(
    id INT NOT NULL DEFAULT RAND(), -- TODO: Figure out a better way to randomly generate the game ID.
    mode VARCHAR(15) NOT NULL DEFAULT 'SINGLEPLAYER',
    timed BIT NOT NULL DEFAULT 0,
    player_count INT NOT NULL DEFAULT 1,
    in_progress BIT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (id),
    CHECK ((mode = 'SINGLEPLAYER' AND player_count = 1) OR mode = 'MULTIPLAYER')
);

-- Specifies each sovereign state's region.
-- Players can choose which regions to be quizzed on.
CREATE TABLE Region
(
    name VARCHAR(20) NOT NULL,
    states_count INT NOT NULL,
    PRIMARY KEY (name),
    UNIQUE (name),
    CHECK (name IN ('AMERICAS', 'EUROPE', 'AFRICA', 'ASIA', 'OCEANIA'))
);

-- Identifies each sovereign state and their region.
-- Flags will be shown for each sovereign state based on regions that the players chose.
CREATE TABLE Sovereign_State
(
    name VARCHAR(100) NOT NULL, -- TODO: sovereign states may have alternative names.
    region VARCHAR(20) NOT NULL,
    PRIMARY KEY (name),
    UNIQUE (name),
    FOREIGN KEY (region) REFERENCES Region(name)
);

-- Intersect table to identify which regions are being used for which games.
CREATE TABLE Game_Region
(
    game_id INT NOT NULL,
    region VARCHAR(20) NOT NULL,
    version INT,
    PRIMARY KEY(game_id, region),
    --UNIQUE(game_id, region),
    FOREIGN KEY(game_id) REFERENCES Game(id) ON DELETE CASCADE,
    FOREIGN KEY(region) REFERENCES Region(name)
);

-- Players in the game.
-- TODO: Might add display_name column for users to create their own name in a game
CREATE TABLE Player
(
    id VARCHAR(40) NOT NULL DEFAULT UUID(), -- TODO: Figure out a better way to randomly generate a user ID.
    game_id INT DEFAULT NULL,
    ready BIT NOT NULL DEFAULT 0,
    points INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (id),
    FOREIGN KEY (game_id) REFERENCES Game(id) ON DELETE SET NULL,
    CHECK ((game_id IS NULL AND points = 0 AND ready = FALSE) OR (game_id IS NOT NULL))
);


-- TODO: Create a trigger for incrementing the player count of a game when
--      a player enters the game.
-- CREATE TRIGGER increment_player_count