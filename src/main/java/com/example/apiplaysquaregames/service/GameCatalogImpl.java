package com.example.apiplaysquaregames.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class GameCatalogImpl implements GameCatalog {

    private TicTacToeGameFactory ticTacToeGameFactory;

    List<Game> games = new ArrayList<>();

    @Override
    public List<Game> getGamesIdentifiers() {
        return games;
    }
}
