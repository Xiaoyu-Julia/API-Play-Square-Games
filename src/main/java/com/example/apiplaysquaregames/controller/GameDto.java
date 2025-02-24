package com.example.apiplaysquaregames.controller;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class GameDto {
    private String gameId;
    private String gameType;

    public GameDto(@NotNull UUID gameId, String gameType) {
        this.gameId = String.valueOf(gameId);
        this.gameType = gameType;
    }


}
