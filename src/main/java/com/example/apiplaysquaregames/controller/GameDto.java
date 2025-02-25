package com.example.apiplaysquaregames.controller;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GameDto {
    private String gameId;
    private String gameType;

    public GameDto(@NotNull String gameId, String gameType) {
        this.gameId = gameId;
        this.gameType = gameType;
    }
    public String getGameId() {
        return gameId;
    }
    public void setGameId(String gameId) {
        this.gameId = gameId;
    }
    public String getGameType() {
        return gameType;
    }
    public void setGameType(String gameType) {
        this.gameType = gameType;
    }



}
