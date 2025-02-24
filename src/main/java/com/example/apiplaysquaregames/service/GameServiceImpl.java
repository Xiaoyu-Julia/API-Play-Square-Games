package com.example.apiplaysquaregames.service;

import com.example.apiplaysquaregames.controller.GameCreationParams;
import com.example.apiplaysquaregames.controller.GameDto;
import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {

    List<Game> games = new ArrayList<Game>();

    @Override
    public Game createGame(GameCreationParams gameCreationParams) {
        if(gameCreationParams.getGameType().equals("tictactoe")){
            return new TicTacToeGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
        } else if (gameCreationParams.getGameType().equals("15 puzzle")) {
            return new TaquinGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
        } else if (gameCreationParams.getGameType().equals("connect4")) {
            return new ConnectFourGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
        } else {
            throw new IllegalArgumentException("game type can not be found");
        }
    }

    @Override
    public List<Game> getAllGamesByStatus(GameStatus status){
        return games.stream().toList();
    }

    @Override
    public  Game getGameById(UUID id) {
        return games.stream().filter(g -> g.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Set<CellPosition> getAllowedMoves(UUID id) {
        return Set.of();
    }

    @Override
    public void moveTo(UUID id, CellPosition position) {

    }
}