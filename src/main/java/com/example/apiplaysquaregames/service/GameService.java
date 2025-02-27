package com.example.apiplaysquaregames.service;

import com.example.apiplaysquaregames.controller.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface GameService {

    Game createGame(GameCreationParams gameCreationParams, UUID playerId);

    List<Game> getAllGamesByStatus(GameStatus status);

    Game getGameById(UUID id);

    Set<CellPosition> getAllowedMoves(UUID id, UUID playerId);

    void moveTo(UUID id, UUID playerId, CellPosition position) throws InvalidPositionException;





}
