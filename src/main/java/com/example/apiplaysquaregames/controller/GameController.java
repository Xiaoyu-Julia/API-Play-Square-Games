package com.example.apiplaysquaregames.controller;

import com.example.apiplaysquaregames.service.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
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
    public GameDto addGame(@RequestBody GameCreationParams gameCreationParams) {
        Game game =  gameService.createGame(gameCreationParams);
        return new GameDto(game.getId().toString(),game.getFactoryId()) ;
    }

    @GetMapping("/games/by-status/{status}")
    public List<GameDto> getAllGamesByStatus(@PathVariable GameStatus status) {
        List<Game> games = gameService.getAllGamesByStatus(status);
        List<GameDto> gameDtos = new ArrayList<>();
        games.forEach(game->{
            gameDtos.add(new GameDto(game.getId().toString(),game.getFactoryId()));
        });
        return gameDtos;
    }

    @GetMapping("/games/{gameId}")
    public GameDto getGame(@PathVariable String gameId) {
        Game game = gameService.getGameById(UUID.fromString(gameId));
        return new GameDto(game.getId().toString(),game.getFactoryId()) ;
    }

    @GetMapping("/games/{gameId}/moves")
    public Set<CellPosition> getAllowedMoves(@PathVariable String gameId) {
        return gameService.getAllowedMoves(UUID.fromString(gameId));
    }

    @PutMapping("/games/{gameId}/moves")
    public void moveTo(@PathVariable String gameId, @RequestBody CellPosition position) {
        gameService.moveTo(UUID.fromString(gameId), position);

    }

}
