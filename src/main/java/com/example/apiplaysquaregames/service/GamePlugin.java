package com.example.apiplaysquaregames.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Locale;
import java.util.OptionalInt;
import java.util.Set;
import java.util.UUID;

public interface GamePlugin {

//    @NotNull
//    GameFactory getGameFactory();
//
//    @NotNull
//    String getId();

    @NotBlank
    String getName(Locale locale);

//    @NotNull
//    Game createGame(
//            OptionalInt playerCount,
//            OptionalInt boardSize
//    );

    Game createGame(
            OptionalInt boardSize,
            Set<UUID> playerIds
    );

}
