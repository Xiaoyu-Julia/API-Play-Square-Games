package com.example.apiplaysquaregames.service;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final Collection<GameFactory> gameFactories;

    public GameCatalogImpl() {
        gameFactories = Collections.synchronizedList(new ArrayList<GameFactory>());
        gameFactories.add(new TicTacToeGameFactory());
        gameFactories.add(new TaquinGameFactory());
        gameFactories.add(new ConnectFourGameFactory());
    }

    @Override
    public Collection<String> getGamesIdentifiers() {
        return gameFactories.stream().map(GameFactory::getGameFactoryId).toList();
    }

    public Collection<GameFactory> getGameFactory(String gameFactoryId) {

        return gameFactories.stream()
                .filter(f -> f.getGameFactoryId().equals(gameFactoryId))
                .collect(Collectors.toList());

    }

//    private TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();

//    @Override
//    public Collection<String> getGamesIdentifiers() {
//
//        return Collections.singletonList(ticTacToeGameFactory.getGameFactoryId());
//    }
}
