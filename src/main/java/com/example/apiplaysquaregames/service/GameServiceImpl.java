package com.example.apiplaysquaregames.service;

import com.example.apiplaysquaregames.controller.GameCreationParams;
import fr.le_campus_numerique.square_games.engine.*;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class GameServiceImpl implements GameService {
    private final Map<UUID, Game> games = new HashMap<>();

    public GameServiceImpl() {
    }

    // List<Game> games = new ArrayList<Game>();

//    @Override
//    public Game createGame(GameCreationParams gameCreationParams) {
//        if(gameCreationParams.getGameType().equals("tictactoe")){
//            Game newGame =  new TicTacToeGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
//            return games.put(newGame.getId(), newGame);
//        } else if (gameCreationParams.getGameType().equals("15 puzzle")) {
//            Game newGame =  new TaquinGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
//            return games.put(newGame.getId(), newGame);
//        } else if (gameCreationParams.getGameType().equals("connect4")) {
//            Game newGame = new ConnectFourGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
//            return games.put(newGame.getId(), newGame);
//        } else {
//            throw new IllegalArgumentException("game type can not be found");
//        }
//
//    }

    @Override
    public Game createGame(GameCreationParams gameCreationParams) {
        Game newGame;
        switch (gameCreationParams.getGameType()) {
            case "tictactoe" -> {
                newGame = new TicTacToeGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
            }
            case "15 puzzle" -> {
                newGame = new TaquinGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
            }
            case "connect4" -> {
                newGame = new ConnectFourGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
            }
            default -> throw new IllegalArgumentException("game type can not be found");
        }
        games.put(newGame.getId(), newGame);
        return newGame;
    }

    @Override
    public List<Game> getAllGamesByStatus(GameStatus status) {

        List<Game> filteredGames = new ArrayList<>();
        for (Game game : games.values()) {
            if (game.getStatus() == status) {
                switch (status) {
                    case SETUP, TERMINATED, ONGOING -> {
                        filteredGames.add(game);
                    }
                }
            }
        }
        return filteredGames;
    }

    @Override
    public Game getGameById(UUID id) {
        return games.get(id);
    }

    @Override
    public Set<CellPosition> getAllowedMoves(UUID id) {
        Collection<Token> remainingTokens = games.get(id).getRemainingTokens();
        return Set.of(remainingTokens.stream().map(Token::getAllowedMoves).flatMap(Collection::stream).toArray(CellPosition[]::new));
    }

    @Override
    public void moveTo(UUID id, CellPosition position) throws InvalidPositionException {

        if (games.containsKey(id) && games.get(id).getStatus() == GameStatus.ONGOING) {

            Game game = games.get(id);

            //if (playerId.equals(game.getCurrentPlayerId())) {

                Optional<Token> tokenToMove = games.get(id).getRemainingTokens().stream()
                        .filter(token -> token.getAllowedMoves().contains(position)
                                && token.getOwnerId().isPresent() /*&& token.getOwnerId().equals(playerId)*/)
                        .findFirst();

                if (tokenToMove.isEmpty())
                    throw new InvalidPositionException(/*playerId + " has no moves"*/position + " is not a valid move");

                tokenToMove.get().moveTo(position);

//            } else {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wait for your turn");
//            }

        } else {
            throw new IllegalArgumentException("Game does not exist");
        }

    }

}
