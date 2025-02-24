package com.example.apiplaysquaregames.service;

import com.example.apiplaysquaregames.controller.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GameService {

    Game createGame(GameCreationParams gameCreationParams);

    List<Game> getAllGamesByStatus(GameStatus status);

    Game getGameById(UUID id);

    Set<CellPosition> getAllowedMoves(UUID id);

    void moveTo(UUID id, CellPosition position);





}
