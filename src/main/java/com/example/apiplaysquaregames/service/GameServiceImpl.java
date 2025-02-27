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

    @Override
    public Game createGame(GameCreationParams gameCreationParams, UUID playerId) {
        Set<UUID> playerIds = new HashSet<>();
        playerIds.add(UUID.randomUUID());
        Game newGame;
        switch (gameCreationParams.getGameType()) {
            case "tictactoe" -> {
                newGame = new TicTacToeGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
               // newGame = new TicTacToeGameFactory().createGame(gameCreationParams.getBoardSize(), Set.of(UUID.randomUUID()));
            }
            case "15 puzzle" -> {
               // newGame = new TaquinGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
                newGame = new TaquinGameFactory().createGame(gameCreationParams.getBoardSize(), Set.of(UUID.randomUUID()));
            }
            case "connect4" -> {
               // newGame = new ConnectFourGameFactory().createGame(gameCreationParams.getPlayerCount(), gameCreationParams.getBoardSize());
                newGame = new ConnectFourGameFactory().createGame(gameCreationParams.getBoardSize(), Set.of(UUID.randomUUID()));
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
//        if (playerId.equals(games.get(id).getCurrentPlayerId())) {
        return games.get(id);
//        }else {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//        }
    }

    @Override
    public Set<CellPosition> getAllowedMoves(UUID id, UUID playerId) {
        Collection<Token> remainingTokens = games.get(id).getRemainingTokens();
        return Set.of(remainingTokens.stream().map(Token::getAllowedMoves)
                .flatMap(Collection::stream)
                .filter(position -> remainingTokens.contains(playerId))
                .toArray(CellPosition[]::new));
    }

    @Override
    public void moveTo(UUID id, UUID playerId, CellPosition position) throws InvalidPositionException {

        if (games.containsKey(id) && games.get(id).getStatus() == GameStatus.ONGOING) {

            Game game = games.get(id);

            if (playerId.equals(game.getCurrentPlayerId())) {

                Optional<Token> tokenToMove = games.get(id).getRemainingTokens().stream()
                        .filter(token -> token.getAllowedMoves().contains(position)
                                && token.getOwnerId().isPresent() && token.getOwnerId().equals(playerId))
                        .findFirst();

                if (tokenToMove.isEmpty())
                    throw new InvalidPositionException(position + " is not a valid move");

                tokenToMove.get().moveTo(position);

//            } else {
//                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wait for your turn");
//            }

            } else {
                throw new IllegalArgumentException("Game does not exist");
            }

        }

    }
}
