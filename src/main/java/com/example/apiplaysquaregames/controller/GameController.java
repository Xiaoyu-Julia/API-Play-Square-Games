package com.example.apiplaysquaregames.controller;

import com.example.apiplaysquaregames.service.GameService;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping("/games")
    public GameDto addGame(@RequestBody GameCreationParams gameCreationParams) {
        Game game =  gameService.createGame(gameCreationParams);
        return new GameDto(game.getId() ,game.getFactoryId()) ;
    }

    @GetMapping("/games?ended=status")
    public List<GameDto> getAllGames(String status) {
        return null;
    }

    @GetMapping("/games/{gameId}")
    public GameDto getGame(@PathVariable String gameId) {
        return null;
    }

    @GetMapping("/games/{gameId}/moves")
    public List<CellPosition> getAllowedMoves(@PathVariable String gameId) {
        return null;
    }

    @PutMapping("/games/{gameId}/moves")
    public String moveTo(@PathVariable String gameId, @RequestBody CellPosition position) {
        return null;
    }

}
