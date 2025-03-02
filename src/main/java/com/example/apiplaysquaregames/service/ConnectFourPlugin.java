package com.example.apiplaysquaregames.service;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class ConnectFourPlugin implements GamePlugin {

    private final MessageSource messageSource;

    @Value("${game.connectFour.default_boardSize}")
    private int default_boardSize;

    @Value("${game.connectFour.default_player_count}")
    private int default_player_count;

    //private ConnectFourGameFactory gameFactory = new ConnectFourGameFactory();

    public ConnectFourPlugin(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
//    @Override
//    public GameFactory getGameFactory() {
//        return this.gameFactory;
//    }
//
//    @Override
//    public String getId() {
//        return this.gameFactory.getGameFactoryId();
//    }

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("connect-four.plugin.name", null, locale);
    }

//    @Override
//    public Game createGame(OptionalInt playerCount, OptionalInt boardSize) {
//        return this.gameFactory.createGame(default_player_count, default_boardSize);
//    }

    @Override
    public Game createGame(OptionalInt boardSize, Set<UUID> playerIds) {
        return new ConnectFourGameFactory().createGame(default_boardSize, playerIds);
    }

}
