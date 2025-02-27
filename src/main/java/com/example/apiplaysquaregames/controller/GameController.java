package com.example.apiplaysquaregames.controller;

import com.example.apiplaysquaregames.service.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/games")
    public GameDto addGame(@RequestBody GameCreationParams gameCreationParams, @RequestHeader("X-UserId") UUID playerId) {
        Game game =  gameService.createGame(gameCreationParams, playerId);
        return new GameDto(game.getId().toString(), game.getFactoryId(), game.getPlayerIds());
    }

    @GetMapping("/games/by-status/{status}")
    public List<GameDto> getAllGamesByStatus(@PathVariable GameStatus status) {
        List<Game> games = gameService.getAllGamesByStatus(status);
        List<GameDto> gameDtos = new ArrayList<>();
        games.forEach(game->{
            gameDtos.add(new GameDto(game.getId().toString(),game.getFactoryId(), game.getPlayerIds()));
        });
        return gameDtos;
    }

    @GetMapping("/games/{gameId}")
    public GameDto getGame(@PathVariable String gameId) {

        Game game = gameService.getGameById(UUID.fromString(gameId));
        return new GameDto(game.getId().toString(),game.getFactoryId(), game.getPlayerIds());
    }

    @GetMapping("/games/{gameId}/moves")
    public Set<CellPosition> getAllowedMoves(@PathVariable UUID gameId) {
        //return gameService.getAllowedMoves(gameId);
        UUID playerId = gameService.getGameById(gameId).getCurrentPlayerId();
        return gameService.getAllowedMoves(gameId, playerId);
    }

    @PutMapping("/games/{gameId}/moves")
    public void moveTo(@PathVariable String gameId, @RequestHeader("X-UserId") UUID playerId, @RequestBody CellPosition position) throws InvalidPositionException {
        if( !gameService.getGameById(UUID.fromString(gameId)).getPlayerIds().contains(playerId))
           throw new IllegalArgumentException("player id invalid");
        gameService.moveTo(UUID.fromString(gameId), playerId, position);

    }

}
