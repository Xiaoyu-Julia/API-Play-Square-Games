package com.example.apiplaysquaregames.controller;


public class GameCreationParams {
    private String gameType;
    private int boardSize;
    private int playerCount;

    public GameCreationParams(String gameType, int boardSize, int playerCount) {
        this.gameType = gameType;
        this.boardSize = boardSize;
        this.playerCount = playerCount;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public int getBoardSize() {
        return boardSize;
    }
    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }
    public int getPlayerCount() {
        return playerCount;
    }
    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }
}

