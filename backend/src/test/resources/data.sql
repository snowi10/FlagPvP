-- Test data for creating an initial game.
INSERT INTO Game (id, mode, timed, player_count, in_progress) VALUES (12345, 'SINGLEPLAYER', 0, 1, false);
INSERT INTO Game (id, mode, timed, player_count, in_progress) VALUES (67890, 'MULTIPLAYER', 0, 1, false);
INSERT INTO Player (id, game_id, ready, points) VALUES ('snowi10', 12345, 0, 0);
INSERT INTO Player (id, game_id, ready, points) VALUES ('eden9', 12345, 0, 0);
INSERT INTO Game_Region (game_id, region) VALUES (12345, 'AMERICAS');
INSERT INTO Game_Region (game_id, region) VALUES (12345, 'ASIA');