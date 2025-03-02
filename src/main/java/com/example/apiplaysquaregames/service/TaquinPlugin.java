package com.example.apiplaysquaregames.service;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.context.MessageSource;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaquinPlugin implements GamePlugin {

    private final MessageSource messageSource;

    @Value("${game.taquin.default_boardSize}")
    private int default_boardSize;

    @Value("${game.taquin.default_player_count}")
    private int default_player_count;

    //private TaquinGameFactory gameFactory = new TaquinGameFactory();

    public TaquinPlugin(MessageSource messageSource) {
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
        return messageSource.getMessage("taquin.plugin.name", null, locale);
    }

//    @Override
//    public Game createGame(OptionalInt playerCount, OptionalInt boardSize) {
//        Game newGame = this.gameFactory.createGame(playerCount.getAsInt(), boardSize.getAsInt());
//        return newGame;
//    }

    @Override
    public Game createGame(OptionalInt boardSize, Set<UUID> playerIds) {
        //return this.gameFactory.createGame(boardSize.getAsInt(), playerIds);
        return new TaquinGameFactory().createGame(default_boardSize, playerIds);
    }

}
