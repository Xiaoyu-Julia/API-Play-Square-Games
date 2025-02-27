package com.example.apiplaysquaregames.controller;

import jakarta.validation.constraints.NotNull;

import java.util.Set;
import java.util.UUID;

public class GameDto {
    private String gameId;
    private String gameType;
    private Set<UUID> playerIds;

    public GameDto(@NotNull String gameId, String gameType, Set<UUID> playerIds) {
        this.gameId = gameId;
        this.gameType = gameType;
        this.playerIds = playerIds;
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
    public Set<UUID> getPlayerIds() {
        return playerIds;
    }




}
