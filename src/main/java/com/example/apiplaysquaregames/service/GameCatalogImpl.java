package com.example.apiplaysquaregames.service;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class GameCatalogImpl implements GameCatalog {

    @Autowired
    private List<GamePlugin> gamePluginList;

    private final Collection<GameFactory> gameFactories;

    public GameCatalogImpl() {
        gameFactories = Collections.synchronizedList(new ArrayList<GameFactory>());
        gameFactories.add(new TicTacToeGameFactory());
        gameFactories.add(new TaquinGameFactory());
        gameFactories.add(new ConnectFourGameFactory());
    }

    @Override
    public List<String> getGamesIdentifiers(Locale locale) {
        ArrayList<String> gamesIdentifiersList = new ArrayList<>();
        for(GamePlugin gamePlugin : gamePluginList) {
            gamesIdentifiersList.add(gamePlugin.getName(locale));
        }

        return gamesIdentifiersList;
    }

    public Collection<GameFactory> getGameFactory(String gameFactoryId) {
        return gameFactories.stream()
                .filter(f -> f.getGameFactoryId().equals(gameFactoryId))
                .collect(Collectors.toList());
    }

//    private TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();

}
